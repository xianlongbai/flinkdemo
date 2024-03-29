package com.bxl.flink.batch

import com.bxl.flink.Utils.WordCountData
import org.apache.flink.api.scala._
import org.apache.flink.api.java.utils.ParameterTool

/**
  * Created by root on 2019/9/22.
  */
object WordCount {

  def main(args: Array[String]) {

//    val params: ParameterTool = ParameterTool.fromArgs(args)
    // set up execution environment
    val env = ExecutionEnvironment.getExecutionEnvironment
    // make parameters available in the web interface
//    env.getConfig.setGlobalJobParameters(params)

    val text =
//      if (params.has("input")) {
//        env.readTextFile(params.get("input"))
//      } else {
//        println("Executing WordCount example with default input data set.")
//        println("Use --input to specify file input.")
//        env.fromCollection(WordCountData.WORDS)
//      }
    env.fromCollection(WordCountData.WORDS)
    val counts = text.flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .groupBy(0)
      .sum(1)
    counts.print()
//    if (params.has("output")) {
//      counts.writeAsCsv(params.get("output"), "\n", " ")
//      env.execute("Scala WordCount Example")
//    } else {
//      println("Printing result to stdout. Use --output to specify output path.")
//      counts.print()
//    }

  }
}
