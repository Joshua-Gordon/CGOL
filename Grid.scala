import java.awt.Color
import java.awt.image.BufferedImage

class Grid(width : Int, height : Int, cells : Array[Array[Cell]]) {

  def this(width : Int, height : Int) = this(width,height,new Array[Array[Cell]](width))

  def getCell(x : Int, y : Int) = cells.apply(x).apply(y)

  def getCells() : Array[Array[Cell]] = cells

  def render() : BufferedImage = {
    val image = new BufferedImage(16*width,16*height,BufferedImage.TYPE_INT_RGB)
    val imageMap : Array[Array[Int]] = cells.map(cellList => cellList.map(cell => if(cell.isAlive()) cell.getColor().getRGB else Color.WHITE.getRGB))
    for(x <- Range(0,width*16); y <- Range(0,width*16)) {
      image.setRGB(x,y,imageMap(x/16)(y/16))
    }
    image
  }

  def update() : Grid = {
    val updatedCells : Array[Array[Cell]] = cells.map(row => row.map(cell => cell.update(this)))
    new Grid(width,height,updatedCells)
  }

  def getNeighbors(x : Int, y : Int) : Int = {
    (for(xi <- Range(-1,2); yi <- Range(-1,2)) yield {
      try {
        if (getCell(x + xi, y + yi).isAlive()) 1 else 0
      } catch {
        case e : ArrayIndexOutOfBoundsException => 0
      }
    }).toList.sum - (if(getCell(x,y).isAlive()) 1 else 0)
  }

  def flipCell(x : Int, y : Int) : Grid = {
    val alive = getCell(x,y).isAlive()
    cells(x)(y) = new Cell(x,y,!alive, cells(x)(y).getColor())
    this
  }

  def printState() : Unit = {
    for(cellList <- cells) {
      for(cell <- cellList) {
        if(cell.isAlive()) println("Cell is alive")
      }
    }
  }

}
