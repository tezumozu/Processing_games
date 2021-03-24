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
  void roll_back(){
    for(i = 0; i < flag.length; i++){
      flag[i] = 0;
    }
  }
  int anime_make_deck(Form[] cards){
    int f = 1;
    if(flag[1] == 0){
      for(i = 1; i <= 53; i++){
          thickness = 0.6;
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
  
  
  int anime_shuffle(Form[] cards){
    int f = 1;
     if(flag[2] == 0){
      for(i = 1; i <= 53; i++){
          thickness = 0.6;
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
  
  int anime_angle_change(Form[] cards){
    int f = 1;
    if(flag[7] == 0){
      for(i = 1; i <= 53; i++){
          thickness = 0.6;
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
  
  
  int anime_to_give_out(Form[] cards,int menber,int start_num){
    int f = 1;
    if(flag[4] == 0){
      for(i = 1; i <= 53; i++){
          thickness = 0.6;
       
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
  
  
  int throw_away(Form[] cards,int A,int B){
    int f = 1;
    float sin = sin(radians(cards[A].get_angle()+90));
    float cos = cos(radians(cards[A].get_angle()+90));
    float speed = 8;
    if(flag[5] == 0){
      for(i = 1; i <= 53; i++){
        thickness = 0.6;
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
  
  int anime_get_card(Form[] cards,int A){
    int f = 1;
    if(flag[5] == 0){
      for(i = 1; i <= 53; i++){
          thickness = 0.6;
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
  
  int anime_title(Title title){
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
  int choose_card(Player player,Form[] cards){
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
  
  void anime_result(int player_num){
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
  
  void anime_hand(int hand_sum,Form card,int player_num,int card_adress,int menber){
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
  
  int move(Form card,float end_x,float end_y,float speed_x,float speed_y){
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
