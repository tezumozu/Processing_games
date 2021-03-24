class Back extends Area{
  private Item box_cap;
  private Item box_sosa;
  private Item box_pot;
  private Item open_cap;
  private Item open_sosa;
  private Item open_pot;
  private Item clock_eleven;
  private Item clock_three;
  private Item left;
  private Item right;
  private Item back;
  
  Back(){
    box_cap = new Item(width/4,height/4*3,20,loadImage("foto/box.PNG"));
    box_sosa = new Item(width/2,height/4*3,20,loadImage("foto/box.PNG"));
    box_pot = new Item(width/4*3,height/4*3,20,loadImage("foto/box.PNG"));
    open_cap = new Item(width/4,height/256*193,20,loadImage("foto/open_A.PNG"));
    open_sosa = new Item(width/2,height/256*193,20,loadImage("foto/open_A.PNG"));
    open_pot = new Item(width/4*3,height/256*193,20,loadImage("foto/open_A.PNG"));
    clock_eleven = new Item(width/2,height/4,10,loadImage("foto/eleven.PNG"));
    clock_three = new Item(width/2,height/4,10,loadImage("foto/three.PNG"));
    
    left = new Item(width/32*2,height/2,8,loadImage("foto/left.PNG"));
    right = new Item(width/32*30,height/2,8,loadImage("foto/right.PNG"));
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  void draw_area(){
    draw_back();
    back.draw_image();
    left.draw_image();
    right.draw_image();
    
    if(!game_flag.get("clock")){
      clock_eleven.draw_image();
    }else{
      clock_three.draw_image();
    }
    
    if(game_flag.get("cap")){
      open_cap.draw_image();
    }else{
      box_cap.draw_image();
    }
    
    if(game_flag.get("pot")){
      open_pot.draw_image();
    }else{
      box_pot.draw_image();
    }
    
    if(game_flag.get("sosa")){
      open_sosa.draw_image();
    }else{
      box_sosa.draw_image();
    }
  }
  
  void mouse_pres(){
    if(box_cap.check_click() || box_sosa.check_click() || box_pot.check_click() || open_cap.check_click() || open_sosa.check_click() || open_pot.check_click()){
      c_area = "Box";
    }
    
    if(clock_eleven.check_click() || clock_three.check_click()){
      c_area = "Clock";
    }
    
    if(back.check_click()){
     c_area = "Front";
    }
    
    if(left.check_click()){
      c_area = "Right";
    }
    if(right.check_click()){
      c_area = "Left";
    }
  }
}
