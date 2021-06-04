package scala.build.bloop.bloopgun

import java.io.OutputStream

import scala.util.control.NonFatal

trait BloopgunLogger {
  def debug(msg: => String): Unit
  def error(msg: => String, ex: Throwable): Unit
  def runnable(name: String)(r: Runnable): Runnable = { () =>
    try r.run()
    catch {
      case NonFatal(e) =>
        error(s"Error running $name", e)
    }
  }
  def coursierInterfaceLogger: coursierapi.Logger
  def bloopBspStdout: Option[OutputStream]
  def bloopBspStderr: Option[OutputStream]
}

object BloopgunLogger {
  def nop: BloopgunLogger =
    new BloopgunLogger {
      def debug(msg: => String) = {}
      def error(msg: => String, ex: Throwable) = {}
      def coursierInterfaceLogger = coursierapi.Logger.nop()
      def bloopBspStdout = None
      def bloopBspStderr = None
    }
}