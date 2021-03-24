import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Old_Maid extends PApplet {

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

public void setup(){
  
  background(255);
  fill(126);
  frameRate(50);
  
  deck = new Deck();
  seen = 0;
  game_flag = 0;
  anime_flag = 0;
  menber = 0;   
}

public void draw(){
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


public int animetion(int seen,Deck deck){
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
class Anime{
  private int i;
  private int progress;
  private int step;
  private float thickness;
  private float move_angle;
  private float speed;
  private int[] flag;
  private int count;
  private float size = 5;
  
  Anime(){
    flag = new int[9];
    for(i = 0; i < flag.length; i++){
      flag[i] = 0;
    }
  }
  public void roll_back(){
    for(i = 0; i < flag.length; i++){
      flag[i] = 0;
    }
  }
  public int anime_make_deck(Form[] cards){
    int f = 1;
    if(flag[1] == 0){
      for(i = 1; i <= 53; i++){
          thickness = 0.6f;
          cards[i].set_x((width/2));
          cards[i].set_y(-cards[i].get_size());
          cards[i].set_angle(30);
          cards[i].turn_over(0);
          cards[i].set_size(width/40*3);
       }
       for(i = 0; i < 7; i++){
         flag[i] = 0;
         if(i == 1){
           flag[i] = 1;
         }
       }
       progress = 1; 
    }    
    speed =  height/5;
    cards[progress].set_y(speed+cards[progress].get_y());
    if(progress != 53 && cards[progress].get_y() >= (height/2) - ((progress-1)*thickness)){
      cards[progress].set_y((height/2) - ((progress-1)*thickness));
      progress++;
        
    }else if(progress == 53 && cards[progress].get_y() >= (height/2) - ( (progress-1)*thickness) ){
      cards[progress].set_y( (height/2)-((progress-1)*thickness));
      flag[1] = 0;
      f = 0;
    }
    return f;
  }
  
  
  public int anime_shuffle(Form[] cards){
    int f = 1;
     if(flag[2] == 0){
      for(i = 1; i <= 53; i++){
          thickness = 0.6f;
          cards[i].set_x((width/2));
          cards[i].set_y((height/2)-((i-1)*thickness));
          cards[i].set_angle(30);
          cards[i].turn_over(0);
         }
       for(i = 0; i < flag.length; i++){
         flag[i] = 0;
         if(i == 2){
           flag[i] = 1;
         }
       }
       step = -floor(frameRate/3);
       progress = 1; 
     }
     
     
         int times = 13;
         if(progress <= times){
           
           switch(step){
             case 1:
               for(i = 14; i <= 39; i++){         
               
                 speed = ((cos((radians((-cards[i].get_angle()+90)%180)))*cards[i].get_h())*2)/2;
                 cards[i].set_x(speed+cards[i].get_x());
                 speed = -((sin((radians((-cards[i].get_angle()+90)%180)))*cards[i].get_h())*2)/2;
                 cards[i].set_y(speed+cards[i].get_y());
                 if(cards[i].get_x() >= ((cos((radians((-cards[i].get_angle()+90)%180)))*cards[i].get_h())*2)+(width/2)+(30*cos((radians((-cards[i].get_angle()+90)%180)))) && cards[i].get_y() <= (height/2)-((sin((radians((-cards[i].get_angle()+90)%180)))*cards[i].get_h())*2)-((i-1)*thickness)-(30*sin((radians((-cards[i].get_angle()+90)%180))))){
                   cards[i].set_x(((cos((radians((-cards[i].get_angle()+90)%180)))*cards[i].get_h())*2)+(width/2)+(30*cos((radians((-cards[i].get_angle()+90)%180)))));
                   cards[i].set_y((height/2)-((sin((radians((-cards[i].get_angle()+90)%180)))*cards[i].get_h())*2)-((i-1)*thickness)-(30*sin((radians((-cards[i].get_angle()+90)%180)))));
                 }
               }
               
               if(cards[39].get_x() == ((cos((radians((-cards[1].get_angle()+90)%180)))*cards[38].get_h())*2)+(width/2)+(30*cos((radians((-cards[1].get_angle()+90)%180)))) && cards[39].get_y() == (height/2)-((sin((radians((-cards[1].get_angle()+90)%180)))*cards[1].get_h())*2)-((38)*thickness)-(30*sin((radians((-cards[1].get_angle()+90)%180))))){
                 step++;
               }
             break;
             
             
             case 2:
               for(i = 14; i <= 39; i++){
                 speed = -(thickness*(13))/2;
                 if(i == 14){
                 }
                 
                 cards[i].set_y(speed+cards[i].get_y());
                 if(cards[i].get_y() <= (height/2)-(thickness*(i+13)) - ((sin((radians((-cards[i].get_angle()+90)%180)))*cards[i].get_h())*2)-(30*sin((radians((-cards[i].get_angle()+90)%180))))){
                   cards[i].set_y((height/2)-(thickness*(i+13)) - ((sin((radians((-cards[i].get_angle()+90)%180)))*cards[i].get_h())*2)-(30*sin((radians((-cards[i].get_angle()+90)%180)))));
                 }
               }
             
               for(i = 53; i >= 40; i--){
                 speed = (thickness*(27))/2;
                 cards[i].set_y(cards[i].get_y()+speed);
                 if(cards[i].get_y() >= (height/2)-(thickness*(i-27))){
                   cards[i].set_y((height/2)-(thickness*(i-27)));
                 }
               }
               if(cards[14].get_y() == (height/2)-(thickness*(27))-((sin((radians((-cards[1].get_angle()+90)%180)))*cards[1].get_h())*2)-(30*sin((radians((-cards[1].get_angle()+90)%180)))) && cards[40].get_y() == (height/2)-(thickness*(13))){
                 step++;
                 
                 //irekae
                 Form[] stac = new Form[55];  
                 for(i = 14; i <= 39; i++){
                   stac[i-13] = cards[i];
                 }
                 for(i = 40; i <= 53; i++){
                   cards[i-26] = cards[i];
                 }
                 for(i = 1; i <= 26; i++){
                   cards[27+i] = stac[i];
                 }
               }
              break;
              
              case 3:
               for(i = 28; i <= 53; i++){
                 speed = -((cos((radians((-cards[i].get_angle()+90)%180)))*cards[i].get_h())*2)/2;
                 cards[i].set_x(speed+cards[i].get_x());
                 speed = ((sin((radians((-cards[i].get_angle()+90)%180)))*cards[i].get_h())*2)/2;
                 cards[i].set_y(speed+cards[i].get_y());
                 if(cards[i].get_x() <= (width/2) && cards[i].get_y() >= (height/2)-((i-1)*thickness)){
                   cards[i].set_x(width/2);
                   cards[i].set_y((height/2)-((i-1)*thickness));
                 }
               }
               
               if(cards[53].get_x() == (width/2) && cards[53].get_y() == (height/2)-((53-1)*thickness)){
                 step = 1;
                 progress++;
               }
               
              break;
              
              default:
                step++;
              break;
           }
         }         
         if(progress > times){
           f = 0;
         }
    return f;
  }
  
  public int anime_angle_change(Form[] cards){
    int f = 1;
    if(flag[7] == 0){
      for(i = 1; i <= 53; i++){
          thickness = 0.6f;
          cards[i].set_x((width/2));
          cards[i].set_y((height/2)-((i-1)*thickness));
          cards[i].set_angle(30);
          cards[i].turn_over(0);
          cards[i].set_size(width/40*3);
         }
       for(i = 0; i < flag.length; i++){
         flag[i] = 0;
         if(i == 7){
           flag[i] = 1;
         }
       }
       move_angle = 30/(frameRate);
       step = 1;
       progress = 1; 
    }
    
    if(step < frameRate/3){
      step++;
      return f;
    }
    
    for(i = 1; i <= 53; i++){
      cards[i].set_angle(cards[i].get_angle()-move_angle);
      cards[i].set_y(thickness*(i-1)/(frameRate)+cards[i].get_y());
      if(cards[i].get_y() >= height/2){
        cards[i].set_y(height/2);
      }
    }
             
    if(cards[53].get_y() == height/2){
      for(i = 1; i <= 53; i++){
        cards[i].set_angle(0);
      }
      f = 0;
    }
    return f;
  }
  
  
  public int anime_to_give_out(Form[] cards,int menber,int start_num){
    int f = 1;
    if(flag[4] == 0){
      for(i = 1; i <= 53; i++){
          thickness = 0.6f;
       
          cards[i].set_x(width/2);
          cards[i].set_y(height/2);
          cards[i].set_angle(0);
          cards[i].turn_over(0);
          cards[i].set_size(width/40*3);
         }
       for(i = 0; i < flag.length; i++){
         flag[i] = 0;
         if(i == 4){
           flag[i] = 1;
         }
       }
       step = 1;
       count = 1;
       progress = 1;
    }
    
    if(menber < start_num || start_num < 0){
      start_num = menber;
    }
    
    speed = 75;
    int player_num = (step-1 + start_num)%menber;
    if(player_num == 0){
      player_num = menber;
    }
    
    
    float angle = (player_num-1)*(360/menber);
    switch(progress){
      case 1:
         cards[step].set_angle(angle);
         cards[step].set_x( cards[step].get_x() + (cos(radians(angle+90))*speed) );
         cards[step].set_y( cards[step].get_y() + (sin(radians(angle+90))*speed) );
         
         if(cards[step].get_x() >= (width/2) + cos(radians(angle+90))*((width/8)*3) &&  cos(radians(angle+90)) >= 0){
           cards[step].set_x((width/2) + cos(radians(angle+90))*((width/8)*3));          
         }else if(cards[step].get_x() <= (width/2) + cos(radians(angle+90))*((width/8)*3) && cos(radians(angle+90)) <= 0 ){
           cards[step].set_x((width/2) + cos(radians(angle+90))*((width/8)*3));           
         }
      
         if(cards[step].get_y() >= (height/2) + sin(radians(angle+90))*((height/8)*3) &&  sin(radians(angle+90)) >= 0){
           cards[step].set_y((height/2) + sin(radians(angle+90))*((height/8)*3));          
         }else if(cards[step].get_y() <= (height/2) + sin(radians(angle+90))*((height/8)*3) && sin(radians(angle+90)) <= 0 ){          
           cards[step].set_y((height/2) + sin(radians(angle+90))*((height/8)*3));
         }
         
         if(cards[step].get_x() == (width/2) + cos(radians(angle+90))*((width/8)*3) && (cards[step].get_y() == (height/2) + sin(radians(angle+90))*((height/8)*3) || (angle+90)%180 == 0)){  
           int hand_count = 1;
           for(i = 1; i <= step; i++){
             if((i-1+start_num) % menber == player_num){
                anime_hand(count,cards[i],player_num,hand_count,menber);
                hand_count++;
             }else if(player_num == menber && (i-1+start_num) % menber == 0){
                anime_hand(count,cards[i],player_num,hand_count,menber);
                hand_count++;
             }
           }
           if(step%menber == 0){
             count++;
           }
           step++;
           
           if(step == 54){
             progress++;
             step = 1;
           }
         }
      break;
    
             
      case 2:
          for(i = 1; i <= step - frameRate/2; i++){         
            if(i > 53){
              break;
            }
            if((i+start_num-1) % menber == 1 && cards[i].get_front() == 0){
              
              cards[i].turn_over(1);              
            }
          
          }
          step++;
          if(step >= 54 + frameRate/2){
            f = 0;
          }
      break;
    }
    if(f == 0){
      flag[4] = 0;
    }
    return f;
  }
  
  
  public int throw_away(Form[] cards,int A,int B){
    int f = 1;
    float sin = sin(radians(cards[A].get_angle()+90));
    float cos = cos(radians(cards[A].get_angle()+90));
    float speed = 8;
    if(flag[5] == 0){
      for(i = 1; i <= 53; i++){
        thickness = 0.6f;
      }
      for(i = 0; i < 7; i++){
        flag[i] = 0;
      }
      cards[A].set_size(width/40*3);
      cards[B].set_size(width/40*3);
      
      cards[A].set_x(width/2 + cos*width/4 + cos(radians(cards[A].get_angle()))*(cards[A].get_w()+5));
      cards[A].set_y(height/2 + sin*height/4 + sin(radians(cards[A].get_angle()))*(cards[A].get_w()+5));
      
      cards[B].set_x(width/2 + cos*height/4 - cos(radians(cards[A].get_angle()))*(cards[B].get_w()+5));      
      cards[B].set_y(height/2 + sin*height/4 - sin(radians(cards[A].get_angle()))*(cards[B].get_w()+5));
    
      cards[A].turn_over(1);
      cards[B].turn_over(1);
      flag[5] = 1;
      
      
    }
    
    float end_x = width/2+cards[A].get_h()*cos;
    float end_y = height/2+cards[A].get_h()*sin;
    cards[A].set_x(cards[A].get_x()-speed*cos);
    cards[B].set_x(cards[B].get_x()-speed*cos);
    cards[A].set_y(cards[A].get_y()-speed*sin);
    cards[B].set_y(cards[B].get_y()-speed*sin);
    
    if(cards[A].get_x() >= end_x + cos(radians(cards[A].get_angle()))*(cards[A].get_w()+5)&& cos <= 0){
      cards[A].set_x( end_x+ cos(radians(cards[A].get_angle()))*(cards[A].get_w()+5));
      cards[B].set_x(end_x - cos(radians(cards[A].get_angle()))*(cards[A].get_w()+5));
    }else if(cards[A].get_x() <=end_x + cos(radians(cards[A].get_angle()))*(cards[A].get_w()+5) && cos >= 0){
      cards[A].set_x(end_x + cos(radians(cards[A].get_angle()))*(cards[A].get_w()+5));
      cards[B].set_x(end_x - cos(radians(cards[A].get_angle()))*(cards[A].get_w()+5));
    }
    
    if(cards[A].get_y() >= end_y + sin(radians(cards[A].get_angle()))*(cards[A].get_w()+5)&& sin <= 0){
      cards[A].set_y( end_y + sin(radians(cards[A].get_angle()))*(cards[A].get_w()+5));
      cards[B].set_y( end_y - sin(radians(cards[A].get_angle()))*(cards[A].get_w()+5));
      
    }else if(cards[A].get_y() <= end_y + sin(radians(cards[A].get_angle()))*(cards[A].get_w()+5) && sin >= 0){
       cards[A].set_y(end_y + sin(radians(cards[A].get_angle()))*(cards[A].get_w()+5));
       cards[B].set_y(end_y - sin(radians(cards[A].get_angle()))*(cards[A].get_w()+5));
     
    }
    if(cards[A].get_x() == end_x + cos(radians(cards[A].get_angle()))*(cards[A].get_w()+5) && (cards[A].get_y() == end_y + sin(radians(cards[A].get_angle()))*(cards[A].get_w()+5) || (cards[A].get_angle()+90)%180 == 0)){
      f = 0;
    }
    
    Form[] stack = new Form[2];
      stack[0] = cards[A];
      stack[1] = cards[B];
      count = 0;
       for(int i = 1; i <= 53; i++){
        for(int j = count; j < 2; j++){
          if(i+count == A || i+count == B){
            count += 1;
          }else{
            break;
          }
        }
        
        if(i > 53-count){
          cards[52] = stack[0];
          cards[53] = stack[1];
          break;
        }
        
        cards[i] = cards[i+count];
      }
    
    
    if(f == 0){
      flag[5] = 0;
    }
    return f;
  }
  
  public int anime_get_card(Form[] cards,int A){
    int f = 1;
    if(flag[5] == 0){
      for(i = 1; i <= 53; i++){
          thickness = 0.6f;
         }
       for(i = 0; i < flag.length; i++){
         flag[i] = 0;
         if(i == 5){
           flag[i] = 1;
         }
       }
       step = 1;
       progress = 1; 
    }
    return f;
  }
  
  public int anime_title(Title title){
    int f = 1;
    if(flag[6] == 0){
       for(i = 0; i < flag.length; i++){
         flag[i] = 0;
         if(i == 6){
           flag[i] = 1;
         }
       }
       step = 1;
       progress = 1; 
    }
    return f;
  }
  public int choose_card(Player player,Form[] cards){
    int choose = 0;
    int hand_sum = player.get_hand_sum();
    int[] hand = player.get_hand();
    
    int[] hand_adress = new int[hand_sum+1];
    for(int i = 1; i <= hand_sum; i++){
      for(int j = 1; j <= 53; j++){
        if(hand[i] == cards[j].get_adress()){
          hand_adress[i] = j;
        }
      }
    }
    
    int count  = 0;
    Form[] stack = new Form[hand_sum+1]; 
    for(int i = 1; i <= 53 - hand_sum; i++){
        for(int j = count; j < hand_sum; j++){
          if(i+count == hand_adress[j+1]){
            stack[j+1] = cards[i+j];
            count += 1;
          }else{
            break;
          }
        }
        cards[i] = cards[i+count];
        
        if(i == 53 - hand_sum){
          for(int j = i+count+1; j <= 53; j++){
            stack[count+1] = cards[j];
            count++;
          }
        }
    }
    
    int j = 1;
    for(int i  = 53 - hand_sum+1; i <= 53; i++){
      cards[i] = stack[j];
      j++;
    }
    
    Card_button[] button = new Card_button[hand_sum+1];
    float w = cards[53].get_w();
    float h = cards[53].get_h();
    
    for(int i = 1; i <= hand_sum; i++){
     float x = width/8+h+w+10 + (width-2*(width/8+h+w+10))/(hand_sum+1)*(i);
     float y = height/2;
     cards[53-hand_sum+i].set_x(x);
     cards[53-hand_sum+i].set_y(y);
     cards[53-hand_sum+i].set_angle(0);     
     button[i] = new Card_button(x,y);
     int flag = button[i].check_flag();
     if(flag != 0){
       choose = floor(random(0,1)*hand_sum)+1;
     }
    }
    
    return choose;
  }
  
  public void anime_result(int player_num){
    String text = "WINNER!!";
    fill(255,50,50);
    stroke(255);
    size+=10;   
    if(player_num == 1){
      text = "LOSER...";
      fill(50,50,255);
      stroke(0);
    }
    
    if(size >= width/6){
      size = width/6;
    }
        
    textSize(size);
    textAlign(CENTER);
    text(text,width/2,height/+2+width/12);
  }
  
  public void anime_hand(int hand_sum,Form card,int player_num,int card_adress,int menber){
    card.set_angle((360/menber)*(player_num-1));
    if(player_num == 1){
      card.set_size(width/10);
      float r =+card.get_w()+(((width-2*card.get_w())/(hand_sum+1))*(card_adress));   
      card.set_x(r);
      card.set_y(height - card.get_h()-25);
    }else{
     
      card.set_size(width/40*3);
      float r = (-width/4) +card.get_w() + (((width/2-2*card.get_w())/(hand_sum+1))*(card_adress));
      card.set_x((width/2) + cos(radians(card.get_angle()+90))*((width/8)*3)+r*cos(radians(card.get_angle())));
      card.set_y((height/2) + sin(radians(card.get_angle()+90))*((height/8)*3)+r*sin(radians(card.get_angle())));
    }    
  }
  
  public int move(Form card,float end_x,float end_y,float speed_x,float speed_y){
    int flag = 0;
    card.set_x(card.get_x()+speed_x);
    card.set_y(card.get_y()+speed_y);
    
    if(end_x > card.get_x() && speed_x > 0){
      card.set_x(end_x); 
    } else if(end_x < card.get_x() && speed_x < 0){
      card.set_x(end_x);
    }
    
    if(end_y > card.get_y() && speed_y > 0){
      card.set_y(end_y); 
    } else if(end_y < card.get_y() && speed_y < 0){
      card.set_y(end_y);
    }
    
    if(end_x == card.get_x() && end_y == card.get_y()){
      flag = 1;
    }
    return flag;
  }
}  
class Button{
  protected float x;
  protected float y;
  protected float w;
  protected float h;
  protected String text;
  protected int flag = 0;
  
  Button(float x,float y,float w,float h,String text){
    this.x = x - w/2;
    this.y = y - h/2;
    this.w = w;
    this.h = h;
    this.text = text;
  }
  
  public void draw_button(){
    if(flag == 1){
      fill(150);
      stroke(230);
    }else{
      fill(230);
      stroke(150);
    }
    
    rect(x,y,w,h);
    float text_size = 0;
    if(w > h){
      text_size = h;
    }else{
      text_size = w;
    }
    
    fill(0);
    stroke(0);
    textSize(text_size/4);
    textAlign(CENTER);
    text(text,x+w/2,y+h/2);
  }
  
  public int check_flag(){
    if(mousePressed && mouseX > x && mouseX < x+w && mouseY > y && mouseY < y+h){
      flag = 1;
    }else{
      flag = 0;
    }
    
   return flag;
  }
}
class Card_button extends Button{
  Card_button(float x,float y){
    super(x,y,0,0,"");
    int form = 53;
    int size = width/40*3;
    float sin = sin(radians(form));
    float cos = cos(radians(form)); 
  
    this.w = size*cos;
    this.h = size*sin;
    this.x = x-(size*cos);
    this.y = y-(size*sin);
  }
  
  public int check_flag(){
    if(mousePressed && mouseX > x && mouseX < x+w*2 && mouseY > y && mouseY < y+h*2){
      flag = 1;
    }else{
      flag = 0;
    }
    
   return flag;
  }
}
class Deck{
  private Status[] status = new Status[54];
  private Form[] form = new Form[54]; 
  Deck(){
    for(int i = 1; i <= 13; i++){
      
      status[i] = new Status(i,"H");
      form[i] = new Form(width/40*3,53,i);
      status[i+13] = new Status(i,"D");
      form[i+13] = new Form(width/40*3,53,i+13);
      status[i+26] = new Status(i,"C");
      form[i+26] = new Form(width/40*3,53,i+26);
      status[i+39] = new Status(i,"S");
      form[i+39] = new Form(width/40*3,53,i+39);
    }
    
    //joker
    status[53] = new Status(0,"Joker");
    form[53] = new Form(width/40*3,53,53);
  }
  
  public Form[] get_form(){
    return form;
  }
  
  public Status[] get_status(){
    return status;
  }
  
  public void deck_shuffle(){
    Status[] copy = new Status[54];
    System.arraycopy(status,1,copy,1,53);
    
    for(int i = 0; i <  53; i++){
     
     int rand =  floor(random(0,1)*(53-i))+1;
     status[i+1] = copy[rand];
     for(int j = rand; j < 53-i; j++){
       copy[j] = copy[j+1];
     }
    }
  }
  
  public void draw_deck(){
    for(int i = 1; i <= 53; i++){
      form[i].draw_card(status);
    }
  }
}
class Form{
  private Mark mark_image = new Mark();
  private float x,y,size,w,h;
  private float sin,cos;
  private float angle;
  private float form;
  private float[] quad_x = new float[4];
  private float[] quad_y = new float[4];
  private int front;
  private int status_adress;  
  Form(float size,float form,int adress){
    status_adress = adress;
    this.size = size;
    this.form = form;
    w = cos(radians(form))*size;
    h = sin(radians(form))*size; 
    x = -w*2;
    y = -h*2;
    front = 0;
    angle = 0;
  }
  
    public float get_x(){
    return x;
  }  
  public float get_y(){
    return y;
  }
  public float get_w(){
    return w;
  }  
  public float get_h(){
    return h;
  }
  public float get_size(){
    return size;
  }
  public float get_angle(){
    return angle;
  }
  public float get_form(){
    return form;
  }
  public float get_front(){
    return front;
  }
  public int get_adress(){
    return status_adress;
  }
  
  
  public void set_x(float x){
      this.x = x;  
  }
  public void set_y(float y){
      this.y = y;
  }
  public void set_angle(float angle){
      this.angle = angle; 
  }
  public void set_size(float size){
    this.size = size;
  }
  
  public void draw_card(Status[] status){
    int num = status[status_adress].get_num();
    String mark = status[status_adress].get_mark();
    sin = sin(radians(form+angle));
    cos = cos(radians(form+angle)); 
    quad_x[0] = x+(size*cos);
    quad_y[0] = y+(size*sin);
    
    sin = sin(radians(180-form+angle));
    cos = cos(radians(180-form+angle)); 
    quad_x[1] = x+(size*cos);
    quad_y[1] = y+(size*sin);
    
    sin = sin(radians(180+form+angle));
    cos = cos(radians(180+form+angle)); 
    quad_x[2] = x+(size*cos);
    quad_y[2] = y+(size*sin);
    
    sin = sin(radians(360-form+angle));
    cos = cos(radians(360-form+angle)); 
    quad_x[3] = x+(size*cos);
    quad_y[3] = y+(size*sin);  
    
    if(front == 1){
      if(num == 0){
        fill(0);
      }else{
        fill(255);
      }
      stroke(0);
    }else{
      fill(100,100,200);
      stroke(255);
    }
    
    quad(quad_x[0],quad_y[0]  ,quad_x[1],quad_y[1],  quad_x[2],quad_y[2],  quad_x[3],quad_y[3]);
    if(front == 1){
      sin = sin(radians(form));
      cos = cos(radians(form));
      float text_size = w/3*1.25f;
      float text_x = -size*cos+text_size;
      float num_y = -size*sin+text_size;
      float mark_y = -size*sin+text_size*2;
      if(mark == "H" || mark == "D"){
        fill(255,0,0);
      }else if(num == 0){
        fill(255);
      }else{
        fill(0);
      }
      textSize(text_size);
      pushMatrix();
      translate(x,y);
      if(num == 0){
        
        for(int i = 0;  i < mark.length(); i++){
           text(mark.charAt(i),text_x,mark_y+(i-1)*text_size);
           rotate(radians(180));
           text(mark.charAt(i),text_x,mark_y+(i-1)*text_size);
           rotate(radians(180));
        }
         
      }else{
        rotate(radians(angle));
        switch(mark){
          case "H":
          mark_image.heart(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "D":
          mark_image.diamond(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "C":
          mark_image.rosetta(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "S":
          mark_image.sword(text_x-text_size/2,mark_y,text_size);
          break;
        }
        switch(num){
          case 1:
            text("A",text_x,num_y);
          break;
          
          case 11:
            text("J",text_x,num_y);
          break;
          
          case 12:
            text("Q",text_x,num_y);
          break;
          
          case 13:
            text("K",text_x,num_y);
          break;
          
          default :
          text(num,text_x,num_y);
        }
        
        rotate(radians(180));
        switch(mark){
          case "H":
          mark_image.heart(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "D":
          mark_image.diamond(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "C":
          mark_image.rosetta(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "S":
          mark_image.sword(text_x-text_size/2,mark_y,text_size);
          break;
        }
        
        switch(num){
          case 1:
            text("A",text_x,num_y);
          break;
          
          case 11:
            text("J",text_x,num_y);
          break;
          
          case 12:
            text("Q",text_x,num_y);
          break;
          
          case 13:
            text("K",text_x,num_y);
          break;
          
          default :
          text(num,text_x,num_y);
        }
        
      }
      popMatrix();
    }
  }
  
  public void turn_over(int f){
       front = f;
  }
}
class Mark{
  float x;
  float y;
  float size;
  Mark(){
  }
  
  public void sword(float x, float y, float size){
     fill(0);
    noStroke();
    
    beginShape();
    vertex(x+size*1/2,y);
    bezierVertex(x+size*18/32,y+size*12/32, x+size*32/32,y+size*10/32, x+size,y+size*20/32);
    bezierVertex(x+size,y+size*29/32, x+size*16/32,y+size*29/32, x+size*16/32,y+size*20/32);
    
    bezierVertex(x+size*17/32,y+size*24/32, x+size*18/32,y+size*28/32, x+size*22/32,y+size);
    bezierVertex(x+size/2,y+size, x+size/2,y+size, x+size*10/32,y+size);
    bezierVertex(x+size*14/32,y+size*28/32, x+size*15/32,y+size*24/32, x+size*16/32,y+size*20/32);
    
    bezierVertex(x+size*16/32,y+size*29/32, x,y+size*29/32, x,y+size*20/32);
    bezierVertex(x,y+size*10/32, x+size*14/32,y+size*12/32, x+size*1/2,y);
    endShape();
  }
  
  public void heart(float x, float y, float size){
    fill(255,100,100);
    noStroke();
    
    beginShape();
    vertex(x+size*1/2,y+size*3/16);
    bezierVertex(x+size*7/8,y-size*1/32, x+size*37/32,y+size*3/8, x+size*27/32,y+size*21/32);
    bezierVertex(x+size*22/32,y+size*25/32, x+size*19/32,y+size*26/32, x+size*1/2,y+size);
    bezierVertex(x+size*13/32,y+size*26/32, x+size*10/32,y+size*25/32, x+size*5/32,y+size*21/32);
    bezierVertex(x-size*5/32,y+size*3/8, x+size*4/32,y-size*1/32, x+size*1/2,y+size*3/16);
    endShape();
  }
  
  public void diamond(float x, float y, float size){
    fill(255,100,100);
    noStroke();
    
    beginShape();
    vertex(x+size*1/2,y);
    bezierVertex(x+size*9/16,y+size*1/8, x+size*11/16,y+size*3/8, x+size*7/8,y+size*1/2);
    bezierVertex(x+size*11/16,y+size*5/8, x+size*9/16,y+size*7/8, x+size*1/2,y+size);
    bezierVertex(x+size*7/16,y+size*7/8, x+size*5/16,y+size*5/8, x+size*1/8,y+size*1/2);
    bezierVertex(x+size*5/16,y+size*3/8, x+size*7/16,y+size*1/8, x+size*1/2,y);
    endShape();
  }
  
  public void rosetta(float x, float y, float size){
    fill(0);
    noStroke();
    ellipse(x+size*1/2,y+size*9/32,size*8/16,size*8/16);
    ellipse(x+size*8/32,y+size*20/32,size*16/32,size*8/16);
    ellipse(x+size*24/32,y+size*20/32,size*16/32,size*8/16);
    rect(x+size*1/4,y+size*8/16,size*1/2,size*1/8);
    
    beginShape();
    vertex(x+size*16/32,y+size*20/32);
    bezierVertex(x+size*17/32,y+size*24/32, x+size*18/32,y+size*28/32, x+size*22/32,y+size);
    bezierVertex(x+size/2,y+size, x+size/2,y+size, x+size*10/32,y+size);
    bezierVertex(x+size*14/32,y+size*28/32, x+size*15/32,y+size*24/32, x+size*16/32,y+size*20/32);
    endShape();
  }
  
  public void joker(float x, float y, float size){
  }
}
class Player{
  private int[] hand = new int[28];
  private int hand_sum;
  private int npc_flag;
  private int win_flag;
  private Anime anime = new Anime();
  private int step = 1;
  private int player_num;
  private int get_hand_adress;
  private int get_hand; 
  private int time;
  Player(int flag,int player_num){
    for(int i = 1; i < 28; i++){
      hand[i] = 0;
    }
    npc_flag = flag;
    this.player_num = player_num;
    hand_sum= 0;
  }
  
  public int get_player_num(){
    return player_num;
  }
  public int[] get_hand(){
    return hand;
  }
  
  public int get_hand_sum(){
    return hand_sum;
  }
  
  public int get_win(){
    return win_flag;
  }
  
  public void set_hand(int adress,int card_adress){
    hand[adress] = card_adress;
    hand_sum++;
  }
  
  
  public int give_hand(int adress,Form[] cards,int menber){
    int re = hand[adress];
    for(int i = adress; i < hand_sum; i++){
      hand[i] =  hand[i+1];
    }
    hand[hand_sum] = 0;
    hand_sum--
    ;
    if(hand_sum == 0){
      win_flag = 1;
    }else{
      int[] card_adress = new int[hand_sum+1];
      for(int i = 1; i <= hand_sum; i++){
        for(int j = 1; j <= 53; j++){
          if(cards[j].get_adress() == hand[i]){
            card_adress[i] = j;
          }
        }
      }
      
      for(int i = 1; i <= hand_sum; i++){
         anime.anime_hand(hand_sum,cards[card_adress[i]],player_num,i,menber);
      }
    }
    return re;
  }
  
  
  public int play_turn(Player enemy,Form[] cards,Status[] status,int menber){
    
    if(win_flag != 0){
      return 0;
    }
    int flag = 1; 
    switch(step){
      case 1:
        if(npc_flag == 1){
          step++;
        }else{
           get_hand_adress = anime.choose_card(enemy,cards);
           if(get_hand_adress != 0){
             step++;
           }
        }
        time = 0;
      break;
      case 2:
        int enemy_hand_sum = enemy.get_hand_sum();
        
        if(npc_flag == 1){
          get_hand_adress = floor(random(0,1)*enemy_hand_sum+1);
          
         }
          get_hand = enemy.give_hand(get_hand_adress,cards,menber);
          hand[hand_sum+1] = get_hand;
          hand_sum++;
          int card_adress  = 0;
          int count = 0;      
          for(int i = 1; i <= 53; i++){
            if(cards[i].get_adress() == get_hand){
              card_adress = i;
              break;
            }    
          }
          Form stack = cards[card_adress];     
          for(int i = 1; i <= 52; i++){
            if(i == card_adress){
                count = 1;
            }
            cards[i] = cards[i+count];
          }
          cards[53] = stack;
          
          int[] cards_adress = new int[hand_sum+1];
          for(int i = 1; i <= hand_sum; i++){
            for(int j = 1; j <= 53; j++){
              
              if(cards[j].get_adress() == hand[i]){
                cards_adress[i] = j;
                cards[j].turn_over(0);
                if(player_num == 1){
                  cards[j].turn_over(1);
                }
              }
            }
          }
          
          for(int i = 1; i <= hand_sum; i++){
            anime.anime_hand(hand_sum,cards[cards_adress[i]],player_num,i,menber);
          }
       
        step++;
        time = 0;
      break;
      case 3:
        if(time >= 30){
          flag = throw_away(cards,status,menber);
        
          if(flag == 0){
            step = 1;
            if(hand_sum == 0){
            win_flag = 1;
            }
          }
        }
        time++;
      break;
    }
    return flag;
  }
  
  
  
  
  public int throw_away(Form[] form,Status[] status,int menber){
    hand_sum = 0;
    for(int i = 1; i < hand.length; i++){
      if(hand[i] != 0){
         hand_sum++;
      }     
    }
    
    if(hand_sum == 0){
      return 0;
    }
    
    int[] form_adress = new int[hand_sum+1];
    for(int i = 1; i <= hand_sum; i++){
      for(int j = 1; j <= 53; j++){
        if(form[j].get_adress() == hand[i]){
          form_adress[i] = j;
        } 
      }
    }
    
    int flag = 1;
    int[] throw_away = new int[54];
    for(int i = 0; i < 54; i++){
      throw_away[i] = 0;
    }
    
    int count  = 0;
    for(int i = 1; i <= hand_sum; i++){
      for(int j = i; j <= hand_sum; j++){
        if(status[hand[i]].get_num() == status[hand[j]].get_num() && i != j){
          count++;
          throw_away[hand[i]] = count;
          throw_away[hand[j]] = count;
          break;
        }
      }
      
      if(throw_away[hand[i]] != 0){
          break;
      }
    }
    
    //no card
    if(count == 0){
      return 0;
    }
    
    for(int i = 1; i <= hand_sum; i++){
      if(throw_away[hand[i]] > 0){
        for(int j = 1; j <= hand_sum; j++){
          if(throw_away[hand[i]] == throw_away[hand[j]] && i != j){
            flag = anime.throw_away(form,form_adress[i],form_adress[j]);
            break;
          }
        }
        break;
      }
    }
    
    if(flag == 0){
      flag = 1;
      count = 0;
      
      for(int i = 1; i <= hand_sum; i++){
        for(int j = count; j < 2; j++){
          if(throw_away[hand[i+count]] != 0){
            count += 1;
          }else{
            break;
          }
        }
        
        if(i > hand_sum-count){
          hand[i] = 0;
          hand[i+1] = 0;
          break;
        }
 
        hand[i] = hand[i+count];
      }
      
      hand_sum -= count;
      
      for(int i = 1; i <= hand_sum; i++){
        for(int j = 1; j <= 53; j++){
          if(form[j].get_adress() == hand[i]){
            form_adress[i] = j;
          } 
         }
      }
      //println(hand_sum);
     
      //soat
      int stack;
      for(int i = 1; i <= hand_sum; i++){
        for(int j = i; j <= hand_sum; j++){
          if(form_adress[i] > form_adress[j]){
            stack = form_adress[i];
            form_adress[i] = form_adress[j];
            form_adress[j] = stack;
          }
        }
      }
      
      
      for(int i = 1; i <= hand_sum; i++){
        anime.anime_hand(hand_sum,form[form_adress[i]],player_num,i,menber);
      }
    }
    return flag;
  }
  
  public String result(){
    String result = "";
    
    if(win_flag == 0){
    }else{
    }
    
    return result;
  }
}
class Status{
   private int num;
   private String mark;
  
  Status(int num,String mark){
    this.num = num;
    this.mark = mark;
  }
  
  public int get_num(){
    return num;
  }
  
  public String get_mark(){
    return mark;
  }
}
class Title{
  String[] title = new String[2];
  float[] x = new float[2];
  float[] y = new float[2];
  float[] angle = new float[2];
  float[] size = new float[2];
  Title(){
    title[0] = "Old";
    title[1] = "Maid";
    for(int i = 0; i < 2; i++){
      size[i] = width/8;
      x[i] = width/2;
      y[i] = height/2;
    }
    
    
  }
  
  public void draw_title(){
    textAlign(CENTER);
    for(int i = 0; i < 2; i++){
      textSize(size[i]);
      text(title[i], x[i],y[i]);
    }
  }
}
  public void settings() {  size(800,800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Old_Maid" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
