private PFont myfont;
private Key_mana key_flag;
private HashMap<String,Area> area;
private ArrayList<Item> itembox = new ArrayList<Item>();
private Item kasutera;
private Item door_key;
HashMap<String,Boolean> game_flag;
String c_area;

void setup(){
  size(800,800);
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

void draw(){
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
void mousePressed(){
  area.get(c_area).mouse_pres();
}
void keyPressed(){
  key_flag.flag_on();
  area.get(c_area).key_pres(key_flag);
}
//リリース処理
void mouseReleased(){
  area.get(c_area).mouse_release();
}

void keyReleased(){
  key_flag.flag_off();
  area.get(c_area).key_release(key_flag);
}


void draw_back(){
  fill(200);
  noStroke();
  rect(0,0,width,height);
  
  fill(200,200,150);
  rect(0,height/4*3,width,height);
}
