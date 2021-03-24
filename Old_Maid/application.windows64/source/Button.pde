class Button{
  protected float x;
  protected float y;
  protected float w;
  protected float h;
  protected String text;
  protected int flag = 0;
  
  Button(float x,float y,float w,float h,String text){
    this.x = x - w/2;
    this.y = y - h/2;
    this.w = w;
    this.h = h;
    this.text = text;
  }
  
  void draw_button(){
    if(flag == 1){
      fill(150);
      stroke(230);
    }else{
      fill(230);
      stroke(150);
    }
    
    rect(x,y,w,h);
    float text_size = 0;
    if(w > h){
      text_size = h;
    }else{
      text_size = w;
    }
    
    fill(0);
    stroke(0);
    textSize(text_size/4);
    textAlign(CENTER);
    text(text,x+w/2,y+h/2);
  }
  
  int check_flag(){
    if(mousePressed && mouseX > x && mouseX < x+w && mouseY > y && mouseY < y+h){
      flag = 1;
    }else{
      flag = 0;
    }
    
   return flag;
  }
}
