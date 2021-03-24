class Button extends Area{
  private Item button_1;
  private Item button_2;
  private ArrayList<Item> buttons = new ArrayList<Item>();
  private Item back;
  private boolean kasutera = false;
  private boolean text_flag = false;
  private Textbox textbox = new Textbox();
  private String[] text = new String[1];
  Button(){
    button_1= new Item(width/4,height/4,25,loadImage("foto/button_1.PNG")); 
    button_2= new Item(width/4*2,height/4,25,loadImage("foto/button_2.PNG")); 
    
    buttons.add(new Item(width/4*3,height/4,25,loadImage("foto/button_3.PNG"))); 
    buttons.add(new Item(width/4,height/4*2,25,loadImage("foto/button_4.PNG"))); 
    buttons.add(new Item(width/4*2,height/4*2,25,loadImage("foto/button_5.PNG"))); 
    buttons.add(new Item(width/4*3,height/4*2,25,loadImage("foto/button_6.PNG"))); 
    buttons.add(new Item(width/4,height/4*3,25,loadImage("foto/button_7.PNG"))); 
    buttons.add(new Item(width/4*2,height/4*3,25,loadImage("foto/button_8.PNG"))); 
    buttons.add(new Item(width/4*3,height/4*3,25,loadImage("foto/button_9.PNG"))); 
    
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  void draw_area(){
    fill(200);
    noStroke();
    rect(0,0,width,height);
    button_1.draw_image();
    button_2.draw_image();
    for(int i = 0; i < buttons.size(); i++){
      buttons.get(i).draw_image();
    }
    back.draw_image();
    
    if(text_flag){
      textbox.draw_text();
    }
  }
  
   void mouse_pres(){
     if(!text_flag){
       if(button_1.check_click()){
         click_button_1();
       }
       if(button_2.check_click()){
         click_button_2();
       }
       for(int i = 0; i < buttons.size(); i++){
         if(buttons.get(i).check_click()){
           click_buttons();
         }
       }
       if(back.check_click()){
         click_back();
       }
       
     }else{
       if(textbox.remove_text()){
        text_flag = false;
        if(kasutera){
          kasutera = false;
          game_flag.put("kasutera",true);
        }
      }
     }
   }
   
   void click_button_1(){
     text_flag = true;
     text = new String[5];
     kasutera = true;
     text[0] = "「……」";
     text[1] = "ノイズが流れ……";
     text[2] = "ドサッ……";
     text[3] = "ん？";
     text[4] = "カステラの食品サンプルを手に入れた";
     textbox.make(text);
   }
   
   void click_button_2(){
     text_flag = true;
     text = new String[5];
     text[0] = "「番号……";
     text[1] = "ハートの女王……";
     text[2] = "白いうさぎ……";
     text[3] = "終わらないお茶会……";
     text[4] = "不思議の国……";
     textbox.make(text);
   }
   
   void click_buttons(){
     text_flag = true;
     text = new String[2];
     text[0] = "「……」";
     text[1] = "ノイズが流れている";
     textbox.make(text);
   }
   
   void click_back(){
    text_flag = false;
    c_area = "Right";
  }
}
