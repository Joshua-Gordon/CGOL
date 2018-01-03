import java.awt.Color

object CellStatic {

  def colorMap(neighb : Int) : Color = {
    if(neighb == 2) new Color(205,0,255, 255) //Purp
    else if(neighb == 3) new Color(0,255,0,255) //Green
    else if(neighb < 2) new Color(255,0,0,255) // Red
    else new Color(255,255,0,255)             //Yellow
  }

}
