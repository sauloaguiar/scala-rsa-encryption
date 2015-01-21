import java.io.{PrintWriter, FileNotFoundException}

import scala.math.BigInt

/**
 * Created by sauloaguiar on 10/8/14.
 */
object main {

  def main(args: Array[String]): Unit = {
    var rsa = new RSA(128)
    var input = readLine("your choice: keys | encrypt | decrypt | exit \n => ")
    while ( input != "exit" ) {
      input match {
        case "keys" => {
          print("How many bits on your key? (Default its 128 bits) \n => ")
          try {
            val bits = readInt()
            if ( bits <= 0 ) {
              throw new NumberFormatException("")
            }
            rsa = new RSA(bits)
            println("So far we have...")
            println(rsa)
          } catch {
            case e: NumberFormatException => println("What's up dude?! Just positive numbers without space!")
          } finally {
            println("Let's start over...")
          }
        }
        case "encrypt" => {
          val datafileName = readLine("Give me the filename to encrypt (with extension): ")
          try {
            val dataByteArray: Array[Byte] = processDataFile(datafileName)
            val encryptedData = rsa.encrypt(dataByteArray)
            generateFile(encryptedData)
          } catch {
            case e: FileNotFoundException => println("Ops! I didn't find anything named like " + datafileName)
          }
        }
        case "decrypt" => {
          val datafileName = readLine("Give me the filename to encrypt (with extension): ")
          try {
            val dataBigIntArray: Array[BigInt] = processEncryptedFile(datafileName)
            var cipher = rsa.decrypt(dataBigIntArray)
            println("Restored data: ")
            println(new String(cipher))
          } catch {
            case e: FileNotFoundException => println("Ops! I didn't find anything named like " + datafileName)
          }
        }
        case _ => println("I don't know what you want...")
      }
      input = readLine("your choice: keys | encrypt | decrypt | exit \n => ")
    }

  }

  def generateFile(encryptedData: Array[BigInt]) {
    val time = String.valueOf(scala.compat.Platform.currentTime).slice(8, 12)
    val fileName = "output_" + time + ".data"
    val output = new PrintWriter(fileName)
    for (value <- encryptedData) {
      output.print(value + " ")
    }
    println("Generated file " + fileName)
    output.close()
  }

  def processDataFile(dataFileName: String) =  {
    val source = readFile(dataFileName)
    val dataByteArray = source.map(_.toByte).toArray
    dataByteArray
  }

  def processEncryptedFile(fileName: String) = {
    val file = scala.io.Source.fromFile(fileName)
    val source = file.getLines.flatMap(_.split("\\W+")).map(x => BigInt(x)).toArray
    file.close()
    source
  }

  def readFile(datafileName: String) = {
    scala.io.Source.fromFile(datafileName)
  }
}