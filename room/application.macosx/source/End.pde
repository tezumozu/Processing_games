class End extends Area{
  End(){
  }
  
  void draw_area(){
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
