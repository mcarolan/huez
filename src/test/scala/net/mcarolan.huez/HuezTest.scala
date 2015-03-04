package net.mcarolan.huez
import org.scalatest._

class HuezTest extends FunSuite with Matchers with Huez {
	import Console._

	test("construct with a foreground colour only") {
		val huez = Red.foreground
		huez should be(ApplicableHuez(Some(Red), None, List()))
	}

	test("overwrite foreground colour when set twice") {
		val huez = Red.foreground and Blue.foreground
		huez should be(ApplicableHuez(Some(Blue), None, List()))
	}

	test("construct with a background colour and foreground colour") {
		val huez = Red.foreground and Blue.background
		huez should be(ApplicableHuez(Some(Red), Some(Blue), List()))
	}

	test("be commutative in setting of foreground and background colours") {
		val huez1 = Red.foreground and Blue.background
		val huez2 = Blue.background and Red.foreground

		huez1 should be(ApplicableHuez(Some(Red), Some(Blue), List()))
		huez2 should be(ApplicableHuez(Some(Red), Some(Blue), List()))
	}

	test("overwrite background colour when set twice") {
		val huez = Red.background and Blue.background
		huez should be(ApplicableHuez(None, Some(Blue), List()))
	}

	test("set style") {
		val huez = Underlined
		huez should be(ApplicableHuez(None, None, List(UNDERLINED)))
	}

	test("set multiple styles") {
		val huez = Underlined and Bold
		huez should be(ApplicableHuez(None, None, List(UNDERLINED, BOLD)))
	}

	test("set background, foreground colours and multiple styles") {
		val huez = Red.foreground and Blue.background and Bold and Underlined
		huez should be(ApplicableHuez(Some(Red), Some(Blue), List(BOLD, UNDERLINED)))
	}

}