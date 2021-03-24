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
  
  int get_player_num(){
    return player_num;
  }
  int[] get_hand(){
    return hand;
  }
  
  int get_hand_sum(){
    return hand_sum;
  }
  
  int get_win(){
    return win_flag;
  }
  
  void set_hand(int adress,int card_adress){
    hand[adress] = card_adress;
    hand_sum++;
  }
  
  
  int give_hand(int adress,Form[] cards,int menber){
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
  
  
  int play_turn(Player enemy,Form[] cards,Status[] status,int menber){
    
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
  
  
  
  
  int throw_away(Form[] form,Status[] status,int menber){
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
  
  String result(){
    String result = "";
    
    if(win_flag == 0){
    }else{
    }
    
    return result;
  }
}
