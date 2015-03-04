package net.mcarolan.huez

import Console._

trait Huez {

	type AnsiColourCode = String

	trait Colour {
		def foregroundAnsiCode: AnsiColourCode
		def backgroundAnsiCode: AnsiColourCode

		def foreground = ApplicableHuez(Some(this), None, List())
		def background = ApplicableHuez(None, Some(this), List())
	}

	case object Red extends Colour {
		val foregroundAnsiCode = RED
		val backgroundAnsiCode = RED_B
	}

	case object Blue extends Colour {
		val foregroundAnsiCode = BLUE
		val backgroundAnsiCode = BLUE_B
	}

	case object Black extends Colour {
		val foregroundAnsiCode = BLACK
		val backgroundAnsiCode = BLACK_B 
	}

	case object Cyan extends Colour {
		val foregroundAnsiCode = CYAN
		val backgroundAnsiCode = CYAN_B 
	}

	case object Green extends Colour {
		val foregroundAnsiCode = GREEN
		val backgroundAnsiCode = GREEN_B 
	}

	case object Magenta extends Colour {
		val foregroundAnsiCode = MAGENTA
		val backgroundAnsiCode = MAGENTA_B 
	}

	case object White extends Colour {
		val foregroundAnsiCode = WHITE
		val backgroundAnsiCode = WHITE_B 
	}

	case object Yellow extends Colour {
		val foregroundAnsiCode = YELLOW
		val backgroundAnsiCode = YELLOW_B 
	}

	object Blinks extends ApplicableHuez(None, None, List(BLINK))
	object Underlined extends ApplicableHuez(None, None, List(UNDERLINED))
	object Bold extends ApplicableHuez(None, None, List(BOLD))
	object Reversed extends ApplicableHuez(None, None, List(REVERSED))
	object Invisible extends ApplicableHuez(None, None, List(INVISIBLE))

	case class ApplicableHuez(foreground: Option[Colour], background: Option[Colour], styles: List[AnsiColourCode]) {
		def and(other: ApplicableHuez) = 
			ApplicableHuez(other.foreground orElse foreground, other.background orElse background, styles ::: other.styles)	

		def apply(s: String): String = {
			val foregroundAnsiCode = foreground.fold("")(_.foregroundAnsiCode)
			val backgroundAnsiCode = background.fold("")(_.backgroundAnsiCode)
			val styleAnsiCode = styles.mkString
			foregroundAnsiCode + backgroundAnsiCode + styleAnsiCode + s + RESET
		}
	}
}

object HuezApp extends App with Huez {

	val warning = Red.background and White.foreground and Bold

	println(warning("Disk nearly full"))
}
