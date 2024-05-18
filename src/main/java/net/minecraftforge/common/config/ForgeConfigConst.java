package net.minecraftforge.common.config;

import com.google.common.base.CharMatcher;

class ForgeConfigConst {

	public static final CharMatcher JAVA_LETTER_OR_DIGIT = new CharMatcher() {
		@Override
		public boolean matches(char c) {
			return Character.isLetterOrDigit(c);
		}
	};

}
