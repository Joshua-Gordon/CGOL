import java.awt.Color

class Cell(x : Int, y : Int, alive : Boolean, color : Color) { //Color of number of neighbors next step

  def update(g : Grid) : Cell = {
    val neighbors = g.getNeighbors(x,y)
    if(alive) {
      if (neighbors < 2 || neighbors > 3) {
        new Cell(x, y, false, CellStatic.colorMap(neighbors))
      } else {
        new Cell(x,y,true,CellStatic.colorMap(neighbors))
      }
    } else {
      if(neighbors == 3) {
        new Cell(x,y,true, CellStatic.colorMap(neighbors))
      } else {
        new Cell(x,y,false,CellStatic.colorMap(neighbors))
      }
    }
  }

  def isAlive() = alive

  def getColor() : Color = color



}
