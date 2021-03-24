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
  
  void draw_area(){
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
  
  
  void mouse_pres(){
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
   
   
  void clock_move(){
      if(short_hand_check(angle+30)){//うーんスパゲティ
        angle += 30;
      }else if(short_hand_check(angle-30)){
        angle -= 30;
      }
      
      if(angle >= 360 || angle <= -360){
        angle = 0;
      }
  }
  
  void mouse_release(){
    if(!text_flag){
      if(clock_flag){
        clock_check();
        clock_flag = false;
      }
    }
    
  }
  
  void clock_check(){
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
  
  boolean short_hand_check(int angle){
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
  
  
  
  float[][] inv(float[] v_a,float[] v_b){//逆行列を求める
    
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
  
  float[] seki(float[][] A,float[][] B){//2行2列＊2行1列の掛け算
    float[] kai = new float[2];
    for(int i = 0;i < 2; i++){
      kai[i] = 0;
      for(int j = 0;j < 2; j++){
        kai[i] += A[i][j] * B[j][0];
      }
    }
    return kai;//[0] = x,[1] = y
  }
  void exit_clock(){
    c_area = "Back";
    if(game_flag.get("clock")){
      angle = 0;
    }else{
      angle = -120;
    }
  }
}
