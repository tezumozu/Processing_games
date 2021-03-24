class Box extends Area{
  private Item off_box_cap;
  private Item off_box_sosa;
  private Item off_box_pot;
  private Item on_box_cap;
  private Item on_box_sosa;
  private Item on_box_pot;
  private Item open_cap;
  private Item open_sosa;
  private Item open_pot;
  private Item back;
  private boolean text_flag = false;
  private Textbox textbox = new Textbox();
  private String[] text = new String[1];
  
  Box(){
    off_box_cap = new Item(width/8,height/2,35,loadImage("foto/box_off.PNG"));
    off_box_sosa = new Item(width/2,height/2,35,loadImage("foto/box_off.PNG"));
    off_box_pot = new Item(width/8*7,height/2,35,loadImage("foto/box_off.PNG"));
    on_box_cap = new Item(width/8,height/2,35,loadImage("foto/box_on.PNG"));
    on_box_sosa = new Item(width/2,height/2,35,loadImage("foto/box_on.PNG"));
    on_box_pot = new Item(width/8*7,height/2,35,loadImage("foto/box_on.PNG"));
    open_cap = new Item(width/8,height/2,30,loadImage("foto/open_B.PNG"));
    open_sosa = new Item(width/2,height/2,30,loadImage("foto/open_B.PNG"));
    open_pot = new Item(width/8*7,height/2,30,loadImage("foto/open_B.PNG"));
    
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  void draw_area(){
    fill(200);
    noStroke();
    rect(0,0,width,height);
    
    fill(200,200,150);
    rect(0,height/4,width,height);
    if(!game_flag.get("clock")){
      off_box_cap.draw_image();
      off_box_sosa.draw_image();
      off_box_pot.draw_image();   
    }else{
      if(game_flag.get("cap")){
        open_cap.draw_image();
      }else{
        on_box_cap.draw_image();
      }
      
      if(game_flag.get("pot")){
        open_pot.draw_image();
      }else{
        on_box_pot.draw_image();
      }
      
      if(game_flag.get("sosa")){
        open_sosa.draw_image();
      }else{
        on_box_sosa.draw_image();
      }
    }   
    
    back.draw_image();
    if(text_flag){
      textbox.draw_text();
    }
  }
  
  void mouse_pres(){
   if(!text_flag){
     if(off_box_cap.check_click()){
       click_box("Panel_cap","cap");
     }
     if(off_box_pot.check_click()){
       click_box("Panel_pot","pot");
     }
     if(off_box_sosa.check_click()){
       click_box("Panel_sosa","sosa");
     }
     if(back.check_click()){
       click_back();
     }
   }else{
     if(textbox.remove_text()){
        text_flag = false;
     }
   }
   
  }
   void click_box(String area,String item){
     if(!game_flag.get(item)){
       if(game_flag.get("clock")){
         c_area = area;
       }else{
         text_flag = true;
         text = new String[2];
         text[0] = "電子ロックがかかっている";
         text[1] = "しかし、電源が入っていないようだ";
         textbox.make(text);
       }
    }
   } 
   void click_back(){
    text_flag = false;
    c_area = "Back";
  }
}
