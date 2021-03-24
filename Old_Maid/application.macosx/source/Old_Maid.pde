private Anime anime;
private Deck deck;
private Player[] player;
private int seen;
private int game_flag;
private int anime_flag;
private int menber;
private int start_num;
private int play_player;
private int winner = 0;
private int count = 0;

void setup(){
  size(800,800);
  background(255);
  fill(126);
  frameRate(50);
  
  deck = new Deck();
  seen = 0;
  game_flag = 0;
  anime_flag = 0;
  menber = 0;   
}

void draw(){
  int i,j;
   fill(20,100,20);
   noStroke();
   rect(0,0,width,height);
   
   
   if(game_flag == 0){
      count++;
      fill(230);
      stroke(200);
      strokeWeight(10);
      rect(width/32*9,width/32*4,width/32*15,width/32*9);
      strokeWeight(1);
      fill(0);
      textSize(width/8);
      textAlign(CENTER);
      text("Old",width/32*13,height/4);
      text("Maid",width/32*19,height/8*3);
      
      if(count >= frameRate){
        Button[] player_num = new Button[5];
        for(i = 1; i <= 4; i++){
          player_num[i] = new Button(width/2,height/2+height/8*(i-1),width/8,height/12,i + "Player");
        }
        
        int button_flag = 0;
        for(i = 2; i <= 4; i++){
          button_flag = player_num[i].check_flag();
          player_num[i].draw_button();
          if(button_flag == 1){
            menber = i;
            break;
          }
        }
        if(button_flag == 1){
         anime = new Anime();
         game_flag = 1;
         seen = 1;
         anime_flag = 1;
         deck = new Deck();
         player = new Player[menber+1];
         player[1] = new Player(0,1);
         for(i = 2; i <= menber; i++){
           player[i] =  new Player(1,i);
         }
         start_num = floor(random(0,1)*menber)+1;
       }
     }
   }else{
    
   
     if(anime_flag == 1 && seen < 4){
       
       anime_flag = animetion(seen,deck);
       if(anime_flag == 0){
         if(seen == 3){
           deck.deck_shuffle();
         }
         seen++;
         anime_flag = 1;
        }
     }
     if(anime_flag == 1 && seen == 4){
       anime_flag = anime.anime_to_give_out(deck.get_form(),menber,start_num);
       if(anime_flag == 0){
         int count = 1;
         
         for(i = 1; i <= 53; i++){
           int player_num = (i-1+start_num)%menber;
           if(player_num == 0){           
             player_num = menber;
           }
           if(player_num == start_num && i != 1){
             count++;
           }
           
           Form[] card;
           card = deck.get_form();
           player[player_num].set_hand(count,card[i].get_adress());
         }
         seen++;
         i = 1;
       }
     }   
     
     if(seen == 5){
    
         int flag = 0;
         for(i = 1; i <= menber; i++){
           flag += player[i].throw_away(deck.get_form(),deck.get_status(),menber); 
         }
         
         if(flag == 0){
           play_player = start_num; 
           winner = 0;
            //frameRate(1);
           seen++;
         }  
     }
     
     if(seen == 6){
      
       int give_player = play_player+1;
       if(give_player > menber - winner){
         give_player = 1;
       }
       int play_flag = 1;
       play_flag = player[play_player].play_turn(player[give_player],deck.get_form(),deck.get_status(),menber);
       if(player[give_player].get_win() != 0){
         for(j = give_player; j < menber; j++){
           player[j] = player[j+1];
         }
         if(play_player > give_player){
           play_player--;
         }
         winner++;           
       }
       
       if(player[play_player].get_win() != 0){
         for(j = play_player; j < menber - winner; j++){
           player[j] = player[j+1];
         }
         play_player--;
         winner++;
       }
      
       if(play_flag == 0){
         play_player++;
         if(play_player > menber - winner){
           play_player = 1;
         }
       }
       
       if(winner == menber - 1){
         seen++;
       }
     }
     deck.draw_deck();
     
     if(seen == 7){
       int button_flag;
       Button exit = new Button(width/2,height/4*3,width/8,height/12,"Exit");
       button_flag = exit.check_flag();
       exit.draw_button();
       anime.anime_result(player[1].get_player_num());
       if(button_flag == 1){
         game_flag = 0;
         seen = 0;
         count = 0;
       }
        
        
       /*int flag = 1;
       flag = player[1].result();
       if(flag == 0){
       }*/
       //size += 10;
     }
  }
}


int animetion(int seen,Deck deck){
  int flag = 0;
  switch(seen){
    case 1:
      flag = anime.anime_make_deck(deck.get_form());
    break;
    
    case 2:
      flag = anime.anime_shuffle(deck.get_form());
    break;
    
    case 3:
      flag = anime.anime_angle_change(deck.get_form());
    break;
  }
  return flag;
}
