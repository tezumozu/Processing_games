class Front extends Area{
  private Item books;
  private Item table;
  private Item food;
  private Item cap;
  private Item pot;
  private Item sosa;
  private Item door;
  private Item left;
  private Item right;
  private Item back;
  private int count = 0;
  private boolean start_flag = true;
  private boolean text_flag = false;
  private Textbox textbox = new Textbox();
  private String[] text = new String[1];
  
  Front(){
    books = new Item(0,height/32*17,50,loadImage("foto/books.PNG"));
    table = new Item(width/2,height/8*7,50,loadImage("foto/table.PNG"));
    food = new Item(width/2,height/4*3,25,loadImage("foto/food_doom.PNG"));
    door = new Item(width/2,height/32*17,50,loadImage("foto/door.PNG"));
    
    cap = new Item(width/128*51,height/128*107,8,loadImage("foto/cap_A.PNG"));
    sosa = new Item(width/8*3,height/16*13,8,loadImage("foto/sara_A.PNG"));
    pot = new Item(width/8*5,height/32*25,8,loadImage("foto/pot.PNG"));
    
    left = new Item(width/32*2,height/2,8,loadImage("foto/left.PNG"));
    right = new Item(width/32*30,height/2,8,loadImage("foto/right.PNG"));
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
    
    
  }
  
  void draw_area(){
    if(start_flag){
      count++;
      if(count > 30){
        start_flag = false;
        text_flag = true;
        text = new String[10];
        text[0] = "………";
        text[1] = "………";
        text[2] = "………ん??";
        text[3] = "ここはどこだ??";
        text[4] = "「ピンポンパンポーン";
        text[5] = "お茶会にお越しいただきありがとうございます";
        text[6] = "しかし、お茶会の開始予定時刻までお時間がございます";
        text[7] = "開始時刻まで少々お待ちください」";
        text[8] = "お茶会?? なんのことだ??";
        text[9] = "俺は明日用事があるんだ　とっとと帰らせてもらう";
        textbox.make(text);
      }
      
    }
    draw_back();
    door.draw_image();
    books.draw_image();
    table.draw_image();
    food.draw_image();
    back.draw_image();
    left.draw_image();
    right.draw_image();
    
    if(game_flag.get("sosa")){
      sosa.draw_image();
    }
    
    if(game_flag.get("cap")){
      cap.draw_image();
    }
    
    if(game_flag.get("pot")){
      pot.draw_image();
    }
    
    if(text_flag){
      textbox.draw_text();
    }
    
  }
  
  void mouse_pres(){
    if(!text_flag && !start_flag){
      if(door.check_click()){
      c_area = "Door";
      }
      if(books.check_click()){
        c_area = "Books";
      }
      if(food.check_click() && !game_flag.get("key")){
        c_area = "Table";
      }
      if(table.check_click() && !game_flag.get("key")){
        c_area = "Table";
      }
      if(left.check_click()){
        c_area = "Left";
      }
      if(right.check_click()){
        c_area = "Right";
      }
      if(back.check_click()){
       c_area = "Back";
      }
    }else if(!start_flag){
      if(textbox.remove_text()){
        text_flag = false;
      }
    }
    
  }
}
