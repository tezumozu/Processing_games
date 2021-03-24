class Tele extends Area{
  
  private Item tl;
  private Item table;
  private Item drawer;
  private Item back;
  private boolean text_flag = false;
  private Textbox textbox = new Textbox();
  private String[] text = new String[1];
  Tele(){
    tl= new Item(width/2,height/2,60,loadImage("foto/tl_B.PNG")); 
    drawer = new Item(width/2,height/64*60,76,loadImage("foto/hikidasi.PNG"));
    table = new Item(width/2,height/8*5,150,loadImage("foto/tele_table.PNG"));
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  void draw_area(){
    fill(200);
    noStroke();
    rect(0,0,width,height);
    table.draw_image();
    tl.draw_image();
    drawer.draw_image();
    back.draw_image();
    
    if(text_flag){
      textbox.draw_text();
    }
  }
  
  
  void mouse_pres(){
    if(!text_flag){
      
      if(tl.check_click()){
        click_tl();
      }
      if(drawer.check_click()){
        click_drawer();
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
   
   
  void click_tl(){
    if(!game_flag.get("clock")){
      text_flag = true;
      text = new String[1];
      text[0] = "電源が入っていないようだ……";
      textbox.make(text);
    }else{
      c_area = "Button";
    }
  }
  
  void click_drawer(){
    text_flag = true;
    text = new String[2];
    text[0] = "メモが入っている";
    text[1] = "「お好きな番号を押すことで登録されている番号におかけします」";
    textbox.make(text);
  }
  
  void click_back(){
    text_flag = false;
    c_area = "Right";
  }
}
