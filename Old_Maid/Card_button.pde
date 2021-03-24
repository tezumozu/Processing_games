class Card_button extends Button{
  Card_button(float x,float y){
    super(x,y,0,0,"");
    int form = 53;
    int size = width/40*3;
    float sin = sin(radians(form));
    float cos = cos(radians(form)); 
  
    this.w = size*cos;
    this.h = size*sin;
    this.x = x-(size*cos);
    this.y = y-(size*sin);
  }
  
  int check_flag(){
    if(mousePressed && mouseX > x && mouseX < x+w*2 && mouseY > y && mouseY < y+h*2){
      flag = 1;
    }else{
      flag = 0;
    }
    
   return flag;
  }
}
