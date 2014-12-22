package com.hitsoft.icu4scala

import org.specs2._
import MessageFormat.format
import java.util.Locale

/**
 * User: smeagol
 * Date: 23.12.14
 * Time: 0:24
 */
class MessageFormatTest extends Specification {
  override def is = s2"""
    1. Properly format from map
        string parameters                ${FmtMap().string}
        number parameters (integer)   ${FmtMap().integer}
        number parameters (currency)  ${FmtMap().currency}

    2. Properly work with variable params
        string parameters                 ${FmtSeq().string}
        number parameters (integer)   ${FmtSeq().integer}
        number parameters (currency)  ${FmtSeq().currency}
  """

  case class FmtMap() {
    def string = {
      format("test {str} param", Map("str" -> "some string")) must_== "test some string param"
    }

    def integer = {
      format("test {int,number,integer} param", Map("int" -> 5)) must_== "test 5 param"
    }

    def currency = {
      format("test {curr,number,currency} param", Map("curr" -> 5f), Locale.US) must_== "test $5.00 param"
    }
  }

  case class FmtSeq() {
    def string = {
      format("test {1} param {0}", "first", "second") must_== "test second param first"
    }

    def integer = {
      Locale.setDefault(Locale.US)
      format("test {1,number,integer} param {0,number}", 1, 5f) must_== "test 5 param 1"
    }

    def currency = {
      Locale.setDefault(Locale.US)
      format("test {0,number,currency} param", 5f) must_== "test $5.00 param"
    }
  }
}
