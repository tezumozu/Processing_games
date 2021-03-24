class Mark{
  float x;
  float y;
  float size;
  Mark(){
  }
  
  void sword(float x, float y, float size){
     fill(0);
    noStroke();
    
    beginShape();
    vertex(x+size*1/2,y);
    bezierVertex(x+size*18/32,y+size*12/32, x+size*32/32,y+size*10/32, x+size,y+size*20/32);
    bezierVertex(x+size,y+size*29/32, x+size*16/32,y+size*29/32, x+size*16/32,y+size*20/32);
    
    bezierVertex(x+size*17/32,y+size*24/32, x+size*18/32,y+size*28/32, x+size*22/32,y+size);
    bezierVertex(x+size/2,y+size, x+size/2,y+size, x+size*10/32,y+size);
    bezierVertex(x+size*14/32,y+size*28/32, x+size*15/32,y+size*24/32, x+size*16/32,y+size*20/32);
    
    bezierVertex(x+size*16/32,y+size*29/32, x,y+size*29/32, x,y+size*20/32);
    bezierVertex(x,y+size*10/32, x+size*14/32,y+size*12/32, x+size*1/2,y);
    endShape();
  }
  
  void heart(float x, float y, float size){
    fill(255,100,100);
    noStroke();
    
    beginShape();
    vertex(x+size*1/2,y+size*3/16);
    bezierVertex(x+size*7/8,y-size*1/32, x+size*37/32,y+size*3/8, x+size*27/32,y+size*21/32);
    bezierVertex(x+size*22/32,y+size*25/32, x+size*19/32,y+size*26/32, x+size*1/2,y+size);
    bezierVertex(x+size*13/32,y+size*26/32, x+size*10/32,y+size*25/32, x+size*5/32,y+size*21/32);
    bezierVertex(x-size*5/32,y+size*3/8, x+size*4/32,y-size*1/32, x+size*1/2,y+size*3/16);
    endShape();
  }
  
  void diamond(float x, float y, float size){
    fill(255,100,100);
    noStroke();
    
    beginShape();
    vertex(x+size*1/2,y);
    bezierVertex(x+size*9/16,y+size*1/8, x+size*11/16,y+size*3/8, x+size*7/8,y+size*1/2);
    bezierVertex(x+size*11/16,y+size*5/8, x+size*9/16,y+size*7/8, x+size*1/2,y+size);
    bezierVertex(x+size*7/16,y+size*7/8, x+size*5/16,y+size*5/8, x+size*1/8,y+size*1/2);
    bezierVertex(x+size*5/16,y+size*3/8, x+size*7/16,y+size*1/8, x+size*1/2,y);
    endShape();
  }
  
  void rosetta(float x, float y, float size){
    fill(0);
    noStroke();
    ellipse(x+size*1/2,y+size*9/32,size*8/16,size*8/16);
    ellipse(x+size*8/32,y+size*20/32,size*16/32,size*8/16);
    ellipse(x+size*24/32,y+size*20/32,size*16/32,size*8/16);
    rect(x+size*1/4,y+size*8/16,size*1/2,size*1/8);
    
    beginShape();
    vertex(x+size*16/32,y+size*20/32);
    bezierVertex(x+size*17/32,y+size*24/32, x+size*18/32,y+size*28/32, x+size*22/32,y+size);
    bezierVertex(x+size/2,y+size, x+size/2,y+size, x+size*10/32,y+size);
    bezierVertex(x+size*14/32,y+size*28/32, x+size*15/32,y+size*24/32, x+size*16/32,y+size*20/32);
    endShape();
  }
  
  void joker(float x, float y, float size){
  }
}
