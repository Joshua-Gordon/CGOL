import java.awt.Color
import java.util.Random

object GridStatic {

  def initialize(width : Int, height : Int) : Grid = {
    val cellLists = new Array[Array[Cell]](width)
    for(i : Int <- cellLists.indices) {
      val cellList : Array[Cell] = new Array[Cell](height)
      for(j : Int <- cellList.indices) {
        cellList.update(j,new Cell(i,j,false, Color.WHITE))
      }
      cellLists.update(i,cellList)
    }
    new Grid(width,height,cellLists)
  }

  def random(width : Int, height : Int) : Grid = {
    val aynRand = new Random()
    val cellLists = new Array[Array[Cell]](width)
    for (i : Int <- cellLists.indices) {
      val cellList : Array[Cell] = new Array[Cell](height)
      for (j : Int <- cellList.indices) {
        cellList.update(j, new Cell(i, j, aynRand.nextBoolean(), Color.WHITE))
      }
      cellLists.update(i, cellList)
    }
    new Grid(width, height, cellLists)
  }

}
