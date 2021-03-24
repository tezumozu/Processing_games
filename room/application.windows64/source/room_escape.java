import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class room_escape extends PApplet {

private PFont myfont;
private Key_mana key_flag;
private HashMap<String,Area> area;
private ArrayList<Item> itembox = new ArrayList<Item>();
private Item kasutera;
private Item door_key;
HashMap<String,Boolean> game_flag;
String c_area;

public void setup(){
  
  frameRate(30);
  
  area = new HashMap <String,Area>();
  game_flag = new HashMap <String,Boolean>();
  
  c_area = "Front";//初期
  
  //フォントの設定
  myfont = createFont("menlo",24,true);
  textFont(myfont);
  key_flag = new Key_mana();
  
  game_flag.put("stert",false);
  game_flag.put("cap",false);
  game_flag.put("pot",false);
  game_flag.put("sosa",false);
  game_flag.put("clock",false);
  game_flag.put("key",false);
  game_flag.put("kasutera",false);
  game_flag.put("open",false);
  
  area.put("Front",new Front());
  area.put("Left",new Left());
  area.put("Right",new Right());
  area.put("Back",new Back());
  area.put("Table",new Table());
  area.put("Door",new Door());
  area.put("Books",new Books());
  area.put("End",new End());
  
  area.put("Book_arisu",new Book("foto/arisu_open.PNG","arisu"));
  area.put("Book_tea",new Book("foto/tea_open.PNG","tea"));
  
  area.put("Clock",new Clock());
  area.put("Box",new Box());
  
  area.put("Panel_cap",new Panel("111212","cap"));
  area.put("Panel_pot",new Panel("73149","pot"));
  area.put("Panel_sosa",new Panel("4132","sosa"));
  
  area.put("Tele",new Tele());
  area.put("Button",new Button());
  
  area.put("futu_preat",new Preat("foto/preat_atari.PNG"));
  area.put("beru_preat",new Preat("foto/hazure_1.PNG"));
  area.put("japa_preat",new Preat("foto/hazure_2.PNG"));
  area.put("sui_preat",new Preat("foto/hazure_3.PNG"));
  area.put("toru_preat",new Preat("foto/hazure_4.PNG"));
  
  itembox.add(new Item(width/16,height/16,8,loadImage("foto/item.PNG")));
  itembox.add(new Item(width/32*5,height/16,8,loadImage("foto/item.PNG")));
  kasutera = new Item(width/32*5,height/16,8,loadImage("foto/syokusan.PNG"));
  door_key = new Item(width/16,height/16,6,loadImage("foto/key.PNG"));
  
}

public void draw(){
  area.get(c_area).draw_area();
  for(int i = 0; i < itembox.size(); i++){
    itembox.get(i).draw_image();
  }
  if(game_flag.get("kasutera")){
    kasutera.draw_image();
  }
  if(game_flag.get("key")){
    door_key.draw_image();
  }
}

//クリック処理
public void mousePressed(){
  area.get(c_area).mouse_pres();
}
public void keyPressed(){
  key_flag.flag_on();
  area.get(c_area).key_pres(key_flag);
}
//リリース処理
public void mouseReleased(){
  area.get(c_area).mouse_release();
}

public void keyReleased(){
  key_flag.flag_off();
  area.get(c_area).key_release(key_flag);
}


public void draw_back(){
  fill(200);
  noStroke();
  rect(0,0,width,height);
  
  fill(200,200,150);
  rect(0,height/4*3,width,height);
}
class Area{
   Area(){
   }
   
   public void draw_area(){
   }
   
   public void mouse_pres(){
   }
   
   public void mouse_release(){
   }
   
   public void key_pres(Key_mana key_flag){
   }
   
   public void key_release(Key_mana key_flag){
   }
}
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
  
  public void draw_area(){
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
  
  public void mouse_pres(){
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
class Book extends Area{
  private Item book;
  private Textbox textbox = new Textbox();
  private String[] text;
  private int seen;
  
  Book(String url,String book_name){
   seen = 0;
   book  = new Item(width/2,height/2,80,loadImage(url));
    if(book_name == "arisu"){
      text = new String[5];
      text[0] = "〜不思議の国のアリス〜";
      text[1] = "1章　白いうさぎ";
      text[2] = "2章　不思議の国";
      text[3] = "３章　終わらないお茶会";
      text[4] = "４章　ハートの女王";
    }else{
      text = new String[4];
      text[0] = "";
      text[1] = "";
      text[2] = "";
      text[3] = "";
    }
    textbox.make(text);
  }
  
  public void draw_area(){
      background(0);
      book.draw_image();
      textbox.draw_text();
  }
  
  public void mouse_pres(){
    
    if(textbox.remove_text()){
        c_area = "Books";
    }
  }
  
}
class Books extends Area{
  private Item bookA;
  private Item bookB;
  private Item back;
  Books(){
    bookA = new Item(width/4*3,height/2,70,loadImage("foto/arisu.PNG"));
    bookB = new Item(width/4,height/2,70,loadImage("foto/tea.PNG"));
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  public void draw_area(){
    background(0);
    bookA.draw_image();
    bookB.draw_image();
    back.draw_image();
  }
  
  public void mouse_pres(){
    if(bookA.check_click()){
      c_area = "Book_arisu";
    }else if(bookB.check_click()){
      c_area = "Book_tea";
    }else if(back.check_click()){
      c_area = "Front";
    }
  }
}
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
  
  public void draw_area(){
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
  
  public void mouse_pres(){
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
   public void click_box(String area,String item){
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
   public void click_back(){
    text_flag = false;
    c_area = "Back";
  }
}
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
  
  public void draw_area(){
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
  
   public void mouse_pres(){
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
   
   public void click_button_1(){
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
   
   public void click_button_2(){
     text_flag = true;
     text = new String[5];
     text[0] = "「番号……";
     text[1] = "ハートの女王……";
     text[2] = "白いうさぎ……";
     text[3] = "終わらないお茶会……";
     text[4] = "不思議の国……";
     textbox.make(text);
   }
   
   public void click_buttons(){
     text_flag = true;
     text = new String[2];
     text[0] = "「……」";
     text[1] = "ノイズが流れている";
     textbox.make(text);
   }
   
   public void click_back(){
    text_flag = false;
    c_area = "Right";
  }
}
class Clock extends Area{
  
  private Item clock;
  private Item short_hand;
  private Item back;
  private boolean in = false;
  private int angle = -120;
  private boolean clock_flag = false;
  private boolean text_flag = false;
  private Textbox textbox = new Textbox();
  private String[] text = new String[1];
   Clock(){
    clock = new Item(width/2,height/2,60,loadImage("foto/clock.PNG")); 
    short_hand = new Item(width/64*5,0,20,loadImage("foto/hari.PNG"));
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  public void draw_area(){
    fill(200);
    noStroke();
    rect(0,0,width,height);
    clock.draw_image();
    back.draw_image();
    
    pushMatrix();
    translate(width/2,height/2);
    rotate(radians(angle));
    short_hand.draw_image();
    popMatrix();
    
    if(text_flag){
      textbox.draw_text();
    }else if(clock_flag){
      clock_move();
    }
    
  }
  
  
  public void mouse_pres(){
    if(!text_flag){
      if(short_hand_check(angle)){
        clock_flag = true;
        println("a");
      }
      
      if(back.check_click()){
        exit_clock();
      }
    }else{
      if(textbox.remove_text()){
        text_flag = false;
      }
    }
   }
   
   
  public void clock_move(){
      if(short_hand_check(angle+30)){//うーんスパゲティ
        angle += 30;
      }else if(short_hand_check(angle-30)){
        angle -= 30;
      }
      
      if(angle >= 360 || angle <= -360){
        angle = 0;
      }
  }
  
  public void mouse_release(){
    if(!text_flag){
      if(clock_flag){
        clock_check();
        clock_flag = false;
      }
    }
    
  }
  
  public void clock_check(){
    if(angle == 0){
      game_flag.put("clock",true);
      text_flag = true;
      text = new String[2];
      text[0] = "カチッ……";
      text[1] = "スイッチが入った音がした……！";
      textbox.make(text);
    }else{
      text_flag = true;
      text = new String[1];
      text[0] = "……";
      textbox.make(text);
    }
    
  }
  
  public boolean short_hand_check(int angle){
    float cos = cos(radians(angle));
    float sin = sin(radians(angle));
    float x = width/2+height/8*cos;
    float y = height/2+height/8*sin;
    float o_x = x + short_hand.get_h()/2*cos(radians(angle+90));
    float o_y = y + short_hand.get_h()/2*sin(radians(angle+90));
    
    float[] v_a = new float[2];//0はx,1はy
    float[] v_b = new float[2];
    v_a[0] = short_hand.get_h() * cos(radians(angle-90));
    v_a[1] = short_hand.get_h() * sin(radians(angle-90));
    
    v_b[0] = short_hand.get_w() * cos(radians(angle+180));
    v_b[1] = short_hand.get_w() * sin(radians(angle+180));
    //問題なし  
    float[][] mouse = new float[2][1];
    mouse[0][0]= mouseX - o_x;
    mouse[1][0] = mouseY - o_y;
    
    
    float[][] inv = inv(v_a,v_b);//逆行列　誤差は考えたくない　一般化はしないできない そんな力はない
    float[] kai = seki(inv,mouse);//　解を求める　x*v_a + y*v_b = mouse となるx,y
    
    if(kai[0] > 0 && kai[0] <= 1 && kai[1] > 0 && kai[1] <= 1){
      return true;
    }
    
      return false;
    //width/2+(height/8*cos),height/2+(height/8*sin)
  }
  
  
  
  public float[][] inv(float[] v_a,float[] v_b){//逆行列を求める
    
    float[][] ex = new float[2][4];
    float[][] inv = new float[2][2];
    
    ex[0][0] = v_a[0];
    ex[0][1] = v_b[0];
    ex[1][0] = v_a[1];
    ex[1][1] = v_b[1];
    
    ex[0][2] = 1;
    ex[0][3] = 0;
    ex[1][2] = 0;
    ex[1][3] = 1;
    
    
    float sca = ex[1][0];//下段先頭を0に
    for(int i = 0; i < 4; i++){
      ex[1][i] = ex[1][i] - ex[0][i] * sca/ex[0][0];
    }
    ex[1][0]=0;//誤差対策
    
    
    sca = ex[0][1];
    for(int i = 0; i < 4; i++){//上段の2列目を0に
    ex[0][i] = ex[0][i] - ex[1][i] * sca/ex[1][1];
    }
    ex[0][1]=0;//誤差対策
     
    for(int i = 0; i < 2; i++){//1にする
      sca = ex[i][i];
      for(int j = 0; j < 4; j++){
        ex[i][j] = ex[i][j]/sca;
      }
     }
    
    inv[0][0]=ex[0][2];
    inv[0][1]=ex[0][3];
    inv[1][0]=ex[1][2];
    inv[1][1]=ex[1][3];//書き出し
    return inv;
  }
  
  public float[] seki(float[][] A,float[][] B){//2行2列＊2行1列の掛け算
    float[] kai = new float[2];
    for(int i = 0;i < 2; i++){
      kai[i] = 0;
      for(int j = 0;j < 2; j++){
        kai[i] += A[i][j] * B[j][0];
      }
    }
    return kai;//[0] = x,[1] = y
  }
  public void exit_clock(){
    c_area = "Back";
    if(game_flag.get("clock")){
      angle = 0;
    }else{
      angle = -120;
    }
  }
}
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
  
  public void draw_area(){
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
  public void mouse_pres(){
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
  
  public void click_door(){
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
class End extends Area{
  End(){
  }
  
  public void draw_area(){
    background(0);
    if(game_flag.get("open")){
      fill(255,0,0);
      textSize(width/10);
      text("CLEAR!!",width/4,height/2);
    }else{
      fill(255,0,0);
      textSize(width/10);
      text("GAME OVER",width/4,height/2);
    }
  }
}
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
  
  public void draw_area(){
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
  
  public void mouse_pres(){
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
class Item{
 private float x;
 private float y;
 private float w;
 private float h;
 private PImage img;
 
 Item(float x,float y,float size,PImage img){
   this.x = x;
   this.y = y;
   this.img = img;
   
   if(img.width > img.height){//
     this.w = width/img.width * img.width * size/100;
     this.h = width/img.width * img.height * size/100;
   }else{
     this.w = height/img.height * img.width * size/100;
     this.h = height/img.height * img.height * size/100;
   }
 }
 
 //座標変更
 public void set_x(float x){
   this.x = x;
 }
 
 public void set_y(float y){
   this.y = y;
 }
 
 //画像サイズ
 public float get_w(){
   return w;
 }
 public float get_h(){
   return h;
 }
 
 //描画
 public void draw_image(){
   //println(w + " " + h);
   image(img,x-w/2,y-h/2,w,h);
   fill(200);
   stroke(255);
   //rect(x-w/2,y-h/2,w,h);
 }
 
 public boolean check_click(){
   if(x - w/2 <= mouseX && x + w/2 >= mouseX && y - h/2 <= mouseY && y + h/2 >= mouseY){
     return true;
   }else
     return false;
   }
 }
class Key_mana{
  private HashMap<Integer,Boolean> key_flag = new HashMap<Integer,Boolean>();
  Key_mana(){
  }
  
  public void flag_on(){
    key_flag.put(keyCode,true);
  }
  
  public void flag_off(){
    key_flag.put(keyCode,true);
  }
  
  public boolean get_flag(int code){
    return key_flag.get(code);
  }
}
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
  
  public void draw_area(){
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
  
  public void mouse_pres(){

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
  
  public void draw_area(){
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
  
  
  public void mouse_pres(){
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
  
  public void click_back(){
    c_area = "Box";
  }
  
  public void key_pres(Key_mana key_flag){//
    
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
  
  public void draw_qestion(String type){
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
  
  public void check_pass(String type){
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
class Preat extends Area{
  private Item preat;
  private Item back;
  Preat(String preat){
    this.preat = new Item(width/2,height/2,100,loadImage(preat)); 
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  public void draw_area(){
    fill(200);
    noStroke();
    rect(0,0,width,height);
    
    preat.draw_image();
    back.draw_image();
  }
  
  public void mouse_pres(){  
   if(back.check_click()){
     click_back();
   }
  }
  
   public void click_back(){
    c_area = "Left";
  }
       
}
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
  
  public void draw_area(){
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
  
  public void mouse_pres(){
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
  
  public void click_tv(){
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
  
  public void draw_area(){
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
  
  public void mouse_pres(){
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
  
  public void draw_area(){
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
  
  
  public void mouse_pres(){
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
   
   
  public void click_tl(){
    if(!game_flag.get("clock")){
      text_flag = true;
      text = new String[1];
      text[0] = "電源が入っていないようだ……";
      textbox.make(text);
    }else{
      c_area = "Button";
    }
  }
  
  public void click_drawer(){
    text_flag = true;
    text = new String[2];
    text[0] = "メモが入っている";
    text[1] = "「お好きな番号を押すことで登録されている番号におかけします」";
    textbox.make(text);
  }
  
  public void click_back(){
    text_flag = false;
    c_area = "Right";
  }
}
class Textbox{
  private String[] text;
  private String font;
  private int count;
  private int text_length;
  private int seen;

// コンストラクタ  
  Textbox(){
    text_length = 1;
    count = 1;
  }
  

  public int get_length(){
    return text[seen].length();
  }
  public int get_text_length(){
    return text_length;  
  }

//テキストの初期化
  public void make(String[] text){
    this.text = text;
    text_length = 1;
    seen = 0;
  }
  
 //テキスト出力
  public void draw_text(){
    textAlign(LEFT);
    if(text[seen].length() != 0){
      //枠組み
      fill(0);
      stroke(255);
      strokeWeight(width/100);
      rect(0,height/4*3,width,height/4);
      
      //文字の追加
      if(text_length < text[seen].length()){
        if(count%3 == 0){
          count = 1;
          text_length++;
        }else{
          count++;
        }
      }
      
     //テキスト出力
     if(text_length > text[seen].length()){
       text_length = text[seen].length();
     }
     font = "";
     for(int i = 0; i < text_length; i++){
       font += text[seen].substring(i,i+1);
     }
    fill(255);
    textSize(width/30);
    text(font,20,height/4*3+40);
    }   
  }
 
  //テキスト削除
  public boolean remove_text(){
    if(text_length >= text[seen].length()){
      seen++;
      text_length = 1;
    }else{//全て出力されていなければ
      text_length = text[seen].length();
    }
    
    if(seen+1 > text.length){
      seen = 0;
      return true;
    }
    return false;
  }
  
}
  public void settings() {  size(800,800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "room_escape" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
