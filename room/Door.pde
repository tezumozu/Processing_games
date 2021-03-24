class Door extends Area{
  private Item door;
  private Item open;
  private boolean text_flag = false;
  private Textbox textbox = new Textbox();
  private String[] text = new String[1];
  private Item back;
  
  Door(){
    door = new Item(width/2,height/2,120,loadImage("foto/door.PNG"));
    open = new Item(width/2,height/2,120,loadImage("foto/door_open.PNG"));
     back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  void draw_area(){
    background(200);
    
    if(game_flag.get("open")){
      open.draw_image();
    }else{
      door.draw_image();
    }
    
    back.draw_image();
    if(text_flag){
      textbox.draw_text();
    }
  }
  
  
  //クリック処理
  void mouse_pres(){
    if(!text_flag){
      if(game_flag.get("open")){
        c_area = "End";
      }  
      
      if(back.check_click()){
        c_area = "Front";
      }else if(door.check_click()){
        click_door();
      }
         
    }else{
      if(textbox.remove_text()){
        text_flag = false;
      }     
    }
    

   }
  
  void click_door(){
    text_flag = true;
    if(game_flag.get("key")){
      text[0] = "扉が開いた！！";
      textbox.make(text);
      game_flag.put("open",true);
    }else{
      text[0] = "鍵がかかっている……";
      textbox.make(text);
    }
  }
  
  
}
