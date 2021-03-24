class Panel extends Area{
  private String pass;
  private String type;
  private String in = "";
  private Item back;
  private Item q_image;
  private boolean text_flag = false;
  private Textbox textbox = new Textbox();
  private String[] text = new String[1];
  
  Panel(String pass,String type){
    this.pass = pass;
    this.type = type;
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
    q_image = new Item(width/2,height/2,20,loadImage("foto/ran.png"));
  }
  
  void draw_area(){
    fill(0);
    stroke(0,255,0);
    strokeWeight(width/100);
    rect(0,0,width,height);
    
    draw_qestion(type);
    back.draw_image();
    
    fill(0,255,0);
    textSize(width/5);
    text(in,width/16,height/2);
    
    if(text_flag){
      textbox.draw_text();
    }
    if(in.length() == pass.length() && !text_flag){
      check_pass(type);
    }
  }
  
  
  void mouse_pres(){
   if(!text_flag){
     if(back.check_click()){
       click_back();
     }
   }else{
     if(textbox.remove_text()){
        text_flag = false;
        if(game_flag.get(type)){
          c_area = "Box";
        }
     }
   } 
  }
  
  void click_back(){
    c_area = "Box";
  }
  
  void key_pres(Key_mana key_flag){//
    
    if(!text_flag){
      for(int i = 0; i < 10; i++){
        if(keyCode == 48+i){
          in += i;
        }
      }
      
      if(keyCode == 8 && in.length() > 0){
        in = in.substring(0,in.length()-1);
      }
    }
  }
  
  void draw_qestion(String type){
    String qestion;
    switch(type){
      case "cap":
        qestion = "過ぎた時間を忘れずに 6桁";
        fill(0,255,0);
        textSize(width/20);
        text(qestion,width/16,height/16*3);
      break;
      
      case "sosa":
        qestion = "電話の順序にしたがって 4桁";
        fill(0,255,0);
        textSize(width/20);
        text(qestion,width/16,height/16*3);
      break;
      
      case "pot":
        qestion = "風車やチューリップ　5桁";
        fill(0,255,0);
        textSize(width/20);
        text(qestion,width/16,height/16*3);
      break;
    }
  }
  
  void check_pass(String type){
    //in = pass;
    text_flag = true;
    if(pass.equals(in)){
      String item = "";
      switch(type){
        case "cap":
          item = "カップ";
        break;
        
        case "sosa":
          item = "ソーサー";
        break;
        
        case "pot":
          item = "ポット";
        break;
      }
      game_flag.put(type,true);
      text = new String[3];
      text[0] = "「電子ロック解除」";
      text[1] = item + "を手に入れた";
      text[2] = item + "をテーブルに置いた";
      textbox.make(text);
    }else{
      in = "";
      text = new String[1];
      text[0] = "「パスコードが間違っています」";
      textbox.make(text);
    }
  }
}
