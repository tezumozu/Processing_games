class Right extends Area{ 
  private Item tv_on;
  private Item tv_off;
  private Item tl;
  private Item left;
  private Item right;
  private Item back;
  private boolean text_flag = false;
  private Textbox textbox = new Textbox();
  private String[] text = new String[1];
  
  Right(){
    text[0] = "";
    tv_on = new Item(width/4*3,height/8*5,50,loadImage("foto/tv_on.PNG"));
    tv_off= new Item(width/4*3,height/8*5,50,loadImage("foto/tv_off.PNG"));
    tl= new Item(width/4,height/8*5,50,loadImage("foto/tl_A.PNG")); 
    left = new Item(width/32*2,height/2,8,loadImage("foto/left.PNG"));
    right = new Item(width/32*30,height/2,8,loadImage("foto/right.PNG"));
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  void draw_area(){
    draw_back();
    back.draw_image();
    left.draw_image();
    right.draw_image();
    tl.draw_image();
    
    if(game_flag.get("clock")){
      tv_on.draw_image();
    }else{
      tv_off.draw_image();
    }
    
    if(text_flag){
      textbox.draw_text();
    }
  }
  
  void mouse_pres(){
    if(!text_flag){
      if(tv_on.check_click() || tv_off.check_click()){
        click_tv();
      }
      
      if(tl.check_click()){
        c_area = "Tele";
      }
      
      if(back.check_click()){
       c_area = "Left";
      }
      
      if(left.check_click()){
        c_area = "Front";
      }
      if(right.check_click()){
        c_area = "Back";
      }
    }else{
      if(textbox.remove_text()){
        text_flag = false;
      }
    }
  }
  
  void click_tv(){
    if(!game_flag.get("clock")){
      text_flag = true;
      text = new String[1];
      text[0] = "電源が入っていないようだ……";
      textbox.make(text);
    }else{
      text_flag = true;
      text = new String[4];
      text[0] = "かxてらいちxん";
      text[1] = "でxわxにばん";
      text[2] = "さんじxおやxxぶんめxxx";
      text[3] = "ノイズ混じりではあるが、どうやら文（ぴー）堂のCMのようだ";
      textbox.make(text);
    }
  }
}
