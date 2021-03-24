class Left extends Area{
  private Item futu;
  private Item beru;
  private Item japa;
  private Item sui;
  private Item toru;
  private Item preat_f;
  private Item preat_b;
  private Item preat_j;
  private Item preat_s;
  private Item preat_t;
  
  private Item left;
  private Item right;
  private Item back;
  
  Left(){
     futu = new Item(width/4,height/4,20,loadImage("foto/furansu.PNG"));
     beru = new Item(width/4*2,height/4,20,loadImage("foto/berugi.PNG"));
     japa = new Item(width/4*3,height/4,20,loadImage("foto/japan.PNG"));
     sui = new Item(width/3,height/4*2,20,loadImage("foto/suisu.PNG"));
     toru = new Item(width/3*2,height/4*2,20,loadImage("foto/toruko.PNG"));
     
     preat_f = new Item(width/4,height/8*3,5,loadImage("foto/preat_atari.PNG"));
     preat_b = new Item(width/4*2,height/8*3,5,loadImage("foto/hazure_1.PNG"));
     preat_j = new Item(width/4*3,height/8*3,5,loadImage("foto/hazure_2.PNG"));
     preat_s = new Item(width/3,height/8*5,5,loadImage("foto/hazure_3.PNG"));
     preat_t = new Item(width/3*2,height/8*5,5,loadImage("foto/hazure_4.PNG"));
    
    left = new Item(width/32*2,height/2,8,loadImage("foto/left.PNG"));
    right = new Item(width/32*30,height/2,8,loadImage("foto/right.PNG"));
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  void draw_area(){
    draw_back();
    futu.draw_image();
    beru.draw_image();
    japa.draw_image();
    sui.draw_image();
    toru.draw_image();
    
    preat_f.draw_image();
    preat_b.draw_image();
    preat_j.draw_image();
    preat_s.draw_image();
    preat_t.draw_image();
    
    back.draw_image();
    left.draw_image();
    right.draw_image();
  }
  
  void mouse_pres(){

    if(futu.check_click()){
      c_area = "futu_preat";
    }
    
    if(beru.check_click()){
      c_area = "beru_preat";
    }
    
    if(japa.check_click()){
      c_area = "japa_preat";
    }
    if(sui.check_click()){
      c_area = "sui_preat";
    }
    if(toru.check_click()){
      c_area = "toru_preat";
    }
     
    if(back.check_click()){
     c_area = "Right";
    }   
    if(left.check_click()){
      c_area = "Back";
    }
    if(right.check_click()){
      c_area = "Front";
    }
  }
  
}
