import java.awt.event._
import javax.swing.{ImageIcon, JFrame, JLabel}

object Main {

  val speed = 100
  val frame = new JFrame("Some Cells")

  def main(args : Array[String]) : Unit = {


    val grid : Grid = GridStatic.random(64,64).flipCell(2,2).flipCell(3,2).flipCell(4,2).flipCell(4,1).flipCell(3,0)
    frame.setDefaultCloseOperation(3)
    val image = new JLabel()
    image.setIcon(new ImageIcon(grid.render()))
    image.setVisible(true)

    frame.add(image)
    frame.pack()
    frame.setVisible(true)

    gameLoop(grid,image,0)
  }
  def gameLoop(grid : Grid, image : JLabel, time : Long) : Grid = {


    frame.addMouseListener(new MouseAdapter {
      override def mouseClicked(e : MouseEvent) : Unit = {

        flipCell(e.getPoint.x/16,e.getPoint.y/16)
      }
    })

    def flipCell(x : Int, y0 : Int) : Unit = {
      val y = y0+4
      grid.getCells.apply(x).update(y,new Cell(x,y,!grid.getCell(x,y).isAlive(), grid.getCells.apply(x).apply(y).getColor()))
    }

    //println("Loop")
    val start = System.currentTimeMillis()
    image.setIcon(new ImageIcon(grid.render()))
    if(time > 1000/speed) {
      return gameLoop(grid.update(),image, 0)
    }
    return gameLoop(grid,image, System.currentTimeMillis() - start + time)
  }

}
