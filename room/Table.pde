class Table extends Area{
  private Item table;
  private Item food;
  private Item cap;
  private Item pot;
  private Item sosa;
  private Item sara;
  private Item makaron;
  private Item kasutera;
  private Item sukon;
  private Item back;
  private boolean farst = true;
  private boolean end_flag = false;
  private boolean key_flag = false;
  private boolean text_flag;
  private Textbox textbox = new Textbox();
  private String[] text = new String[4];
  
  Table(){
    text_flag = false;
    table = new Item(width/2,height/4*3,150,loadImage("foto/table.PNG"));
    food = new Item(width/2,height/8*3,80,loadImage("foto/food_doom.PNG"));
    sara = new Item(width/2,height/16*7,80,loadImage("foto/sara_A.PNG"));
    
    cap = new Item(width/4,height/32*17,25,loadImage("foto/cap_A.PNG"));
    sosa = new Item(width/128*31,height/32*18,25,loadImage("foto/sara_A.PNG"));
    pot = new Item(width/32*25,height/2,25,loadImage("foto/pot.PNG"));
    
    makaron = new Item(width/2,height/16*7,10,loadImage("foto/makaron.PNG"));
    kasutera = new Item(width/8*3,height/16*7,17,loadImage("foto/kasutera.PNG"));
    sukon = new Item(width/8*5,height/16*7,15,loadImage("foto/sukon.PNG"));
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
    
    
    text[0] = "今まで開かなかった銀のフードドームが開いている";
    text[1] = "なにやらメモがある";
    text[2] = "「お茶会のお菓子を用意しました";
    text[3] = "正しいものをお食べください」";
  }
  
  void draw_area(){
    background(200);
    table.draw_image();
    back.draw_image();
    if(game_flag.get("cap")&&game_flag.get("sosa")&&game_flag.get("pot")){
      sara.draw_image();
      makaron.draw_image();
      kasutera.draw_image();
      sukon.draw_image();
      
      //textの生成
      if(farst){
        farst = false;
        text_flag = true;
        textbox.make(text);
      }
      
    }else{
      food.draw_image();
    }
    if(game_flag.get("sosa")){
      sosa.draw_image();
    }
    if(game_flag.get("cap")){
      cap.draw_image();
    }
    if(game_flag.get("pot")){
      pot.draw_image();
    }
    
    //text
    if(text_flag){
      textbox.draw_text();
    }
  }
  
  void mouse_pres(){
    if(game_flag.get("cap")&&game_flag.get("sosa")&&game_flag.get("pot") && !text_flag){
      if(kasutera.check_click()){
       eat_key();
      }else if(sukon.check_click()){
       eat_doku();
      }else if(makaron.check_click()){
       eat_doku();
      }
    }
    if(back.check_click() && !text_flag){
      c_area = "Front";
    }
    if(text_flag){  
      if(textbox.remove_text()){
        text_flag = false;
        end();
      }
    }
  }
  
  private void eat_doku(){
    end_flag = true;
    text_flag = true;
    text = new String[7];
    text[0] = "よしこれにしよう";
    text[1] = "……";
    text[2] = "…………";
    text[3] = "………………";
    text[4] = "………………!?";
    text[5] = "うっ！！";
    text[6] = "これは青酸カリ……！！";
    textbox.make(text);
  }
    
  private void eat_key(){
    end_flag = true;
    text_flag = true;
    key_flag = true;
    text = new String[7];
    text[0] = "よしこれにしよう";
    text[1] = "……";
    text[2] = "…………";
    text[3] = "………………";
    text[4] = "………………!?";
    text[5] = "なにやら硬いものが…";
    text[6] = "「扉の鍵」を手に入れた";
    textbox.make(text);
  }
  
  private void end(){
    if(end_flag){
      if(key_flag){
        game_flag.put("key",true);
        c_area = "Front";
      }else{
        c_area = "End";
      }
    }
  }
}
