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
 void set_x(float x){
   this.x = x;
 }
 
 void set_y(float y){
   this.y = y;
 }
 
 //画像サイズ
 float get_w(){
   return w;
 }
 float get_h(){
   return h;
 }
 
 //描画
 void draw_image(){
   //println(w + " " + h);
   image(img,x-w/2,y-h/2,w,h);
   fill(200);
   stroke(255);
   //rect(x-w/2,y-h/2,w,h);
 }
 
 boolean check_click(){
   if(x - w/2 <= mouseX && x + w/2 >= mouseX && y - h/2 <= mouseY && y + h/2 >= mouseY){
     return true;
   }else
     return false;
   }
 }
