class Title{
  String[] title = new String[2];
  float[] x = new float[2];
  float[] y = new float[2];
  float[] angle = new float[2];
  float[] size = new float[2];
  Title(){
    title[0] = "Old";
    title[1] = "Maid";
    for(int i = 0; i < 2; i++){
      size[i] = width/8;
      x[i] = width/2;
      y[i] = height/2;
    }
    
    
  }
  
  void draw_title(){
    textAlign(CENTER);
    for(int i = 0; i < 2; i++){
      textSize(size[i]);
      text(title[i], x[i],y[i]);
    }
  }
}
