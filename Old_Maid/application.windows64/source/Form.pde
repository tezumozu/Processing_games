class Form{
  private Mark mark_image = new Mark();
  private float x,y,size,w,h;
  private float sin,cos;
  private float angle;
  private float form;
  private float[] quad_x = new float[4];
  private float[] quad_y = new float[4];
  private int front;
  private int status_adress;  
  Form(float size,float form,int adress){
    status_adress = adress;
    this.size = size;
    this.form = form;
    w = cos(radians(form))*size;
    h = sin(radians(form))*size; 
    x = -w*2;
    y = -h*2;
    front = 0;
    angle = 0;
  }
  
    public float get_x(){
    return x;
  }  
  public float get_y(){
    return y;
  }
  public float get_w(){
    return w;
  }  
  public float get_h(){
    return h;
  }
  public float get_size(){
    return size;
  }
  public float get_angle(){
    return angle;
  }
  public float get_form(){
    return form;
  }
  public float get_front(){
    return front;
  }
  public int get_adress(){
    return status_adress;
  }
  
  
  public void set_x(float x){
      this.x = x;  
  }
  public void set_y(float y){
      this.y = y;
  }
  public void set_angle(float angle){
      this.angle = angle; 
  }
  public void set_size(float size){
    this.size = size;
  }
  
  void draw_card(Status[] status){
    int num = status[status_adress].get_num();
    String mark = status[status_adress].get_mark();
    sin = sin(radians(form+angle));
    cos = cos(radians(form+angle)); 
    quad_x[0] = x+(size*cos);
    quad_y[0] = y+(size*sin);
    
    sin = sin(radians(180-form+angle));
    cos = cos(radians(180-form+angle)); 
    quad_x[1] = x+(size*cos);
    quad_y[1] = y+(size*sin);
    
    sin = sin(radians(180+form+angle));
    cos = cos(radians(180+form+angle)); 
    quad_x[2] = x+(size*cos);
    quad_y[2] = y+(size*sin);
    
    sin = sin(radians(360-form+angle));
    cos = cos(radians(360-form+angle)); 
    quad_x[3] = x+(size*cos);
    quad_y[3] = y+(size*sin);  
    
    if(front == 1){
      if(num == 0){
        fill(0);
      }else{
        fill(255);
      }
      stroke(0);
    }else{
      fill(100,100,200);
      stroke(255);
    }
    
    quad(quad_x[0],quad_y[0]  ,quad_x[1],quad_y[1],  quad_x[2],quad_y[2],  quad_x[3],quad_y[3]);
    if(front == 1){
      sin = sin(radians(form));
      cos = cos(radians(form));
      float text_size = w/3*1.25;
      float text_x = -size*cos+text_size;
      float num_y = -size*sin+text_size;
      float mark_y = -size*sin+text_size*2;
      if(mark == "H" || mark == "D"){
        fill(255,0,0);
      }else if(num == 0){
        fill(255);
      }else{
        fill(0);
      }
      textSize(text_size);
      pushMatrix();
      translate(x,y);
      if(num == 0){
        
        for(int i = 0;  i < mark.length(); i++){
           text(mark.charAt(i),text_x,mark_y+(i-1)*text_size);
           rotate(radians(180));
           text(mark.charAt(i),text_x,mark_y+(i-1)*text_size);
           rotate(radians(180));
        }
         
      }else{
        rotate(radians(angle));
        switch(mark){
          case "H":
          mark_image.heart(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "D":
          mark_image.diamond(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "C":
          mark_image.rosetta(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "S":
          mark_image.sword(text_x-text_size/2,mark_y,text_size);
          break;
        }
        switch(num){
          case 1:
            text("A",text_x,num_y);
          break;
          
          case 11:
            text("J",text_x,num_y);
          break;
          
          case 12:
            text("Q",text_x,num_y);
          break;
          
          case 13:
            text("K",text_x,num_y);
          break;
          
          default :
          text(num,text_x,num_y);
        }
        
        rotate(radians(180));
        switch(mark){
          case "H":
          mark_image.heart(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "D":
          mark_image.diamond(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "C":
          mark_image.rosetta(text_x-text_size/2,mark_y,text_size);
          break;
          
          case "S":
          mark_image.sword(text_x-text_size/2,mark_y,text_size);
          break;
        }
        
        switch(num){
          case 1:
            text("A",text_x,num_y);
          break;
          
          case 11:
            text("J",text_x,num_y);
          break;
          
          case 12:
            text("Q",text_x,num_y);
          break;
          
          case 13:
            text("K",text_x,num_y);
          break;
          
          default :
          text(num,text_x,num_y);
        }
        
      }
      popMatrix();
    }
  }
  
  void turn_over(int f){
       front = f;
  }
}
