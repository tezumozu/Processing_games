class Preat extends Area{
  private Item preat;
  private Item back;
  Preat(String preat){
    this.preat = new Item(width/2,height/2,100,loadImage(preat)); 
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  void draw_area(){
    fill(200);
    noStroke();
    rect(0,0,width,height);
    
    preat.draw_image();
    back.draw_image();
  }
  
  void mouse_pres(){  
   if(back.check_click()){
     click_back();
   }
  }
  
   void click_back(){
    c_area = "Left";
  }
       
}
