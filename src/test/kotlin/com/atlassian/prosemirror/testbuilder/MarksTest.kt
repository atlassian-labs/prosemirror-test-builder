package com.atlassian.prosemirror.testbuilder

import com.atlassian.prosemirror.model.Mark
import com.atlassian.prosemirror.testbuilder.PMNodeBuilder.Companion.doc
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class MarksTest {
    @BeforeTest
    fun beforeTest() {
        PMNodeBuilder.clean()
    }

    @Test
    @Ignore("need to build test schema as test-marks.ts")
    fun `deduplicates identical marks`() {
        assertThat(Mark.sameSet(emptyList(), emptyList())).isTrue

        val actual = doc { p { a(href = "/foo") { a(href = "/foo") { +"click <p>here" } } } }
        val expected = doc { p { a(href = "/foo") { +"click here" } } }

        assertThat(actual).isEqualTo(expected)
        assertThat(1).isEqualTo(actual.content.firstChild?.marks?.size)
    }

    @Test
    @Ignore("need to build test schema as test-marks.ts")
    fun `marks of same type but different attributes are distinct`() {
        val actual = doc { p { a(href = "/foo") { a(href = "/bar") { +"click <p>here" } } } }
        assertThat(2).isEqualTo(actual.content.firstChild?.marks?.size)
    }

}
