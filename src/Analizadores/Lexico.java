package Analizadores;
import java_cup.runtime.Symbol; 


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
 
    yyline = 1; 
    yychar = 1; 
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"46:9,43,42,46:2,43,46:18,49,21,45,46:2,18,23,47,6,7,15,13,3,14,4,16,48:10,5" +
",2,19,1,20,12,46,33,44,30,35,28,36,50,38,25,41,50,29,37,26,27,34,50,32,24,3" +
"1,39,40,50:4,8,46,9,17,46:2,33,44,30,35,28,36,50,38,25,41,50,29,37,26,27,34" +
",50,32,24,31,39,40,50:4,10,22,11,46:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,160,
"0,1,2,1:11,3,4,1,5,1:2,6,7,8,9,10,1,11,12,1:8,13,1,14,1,15,1,16:15,17,18,19" +
",20,21,22,21,1,23,14:2,24,15,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,4" +
"0,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,6" +
"5,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,9" +
"0,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,11" +
"1,112,16,113")[0];

	private int yy_nxt[][] = unpackFromString(114,51,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,58,24,59,158:2," +
"138,158,105,106,148,158,107,124,125,159,126,158,108,158,25,26,149,61,64,67," +
"27,26,158,-1:52,28,-1:62,29,-1:51,30,-1:51,57,60,-1:35,31,-1:50,32,-1:50,33" +
",-1:71,34,-1:52,158,36,158:2,150,158:13,-1:2,158,-1:3,158,-1,158,-1:43,26,-" +
"1:5,26,-1:5,69,-1:19,158:18,-1:2,158,-1:3,27,-1,158,-1:24,158:2,62,158:15,-" +
"1:2,158,-1:3,158,-1,158,-1,66:41,-1,66:4,38,66:3,-1:8,40,-1:13,40,-1:25,40:" +
"2,-1:25,158:18,-1:2,158,-1:3,158,-1,158,-1,57:14,71,-1,57:34,-1:23,35,-1:51" +
",158:13,151,158:4,-1:2,158,-1:3,158,-1,158,-1,60:41,39,60:8,-1,63:41,-1,63:" +
"2,37,63:5,-1:24,158:3,42,158:14,-1:2,158,-1:3,158,-1,158,-1:24,158:3,43,158" +
":14,-1:2,158,-1:3,158,-1,158,-1:24,158:4,44,158:13,-1:2,158,-1:3,158,-1,158" +
",-1:24,158:9,45,158:8,-1:2,158,-1:3,158,-1,158,-1:16,41,-1:58,158:11,46,158" +
":6,-1:2,158,-1:3,158,-1,158,-1:24,158:4,47,158:13,-1:2,158,-1:3,158,-1,158," +
"-1:24,158:8,48,158:9,-1:2,158,-1:3,158,-1,158,-1:24,158:3,47,158:14,-1:2,15" +
"8,-1:3,158,-1,158,-1:24,158:8,49,158:9,-1:2,158,-1:3,158,-1,158,-1:24,158:9" +
",47,158:8,-1:2,158,-1:3,158,-1,158,-1:24,158:3,50,158:14,-1:2,158,-1:3,158," +
"-1,158,-1:24,158:8,51,158:9,-1:2,158,-1:3,158,-1,158,-1:24,158:8,52,158:9,-" +
"1:2,158,-1:3,158,-1,158,-1:24,158:8,53,158:9,-1:2,158,-1:3,158,-1,158,-1:24" +
",158:8,47,158:9,-1:2,158,-1:3,158,-1,158,-1:24,54,158:17,-1:2,158,-1:3,158," +
"-1,158,-1:24,158:8,55,158:9,-1:2,158,-1:3,158,-1,158,-1:24,158:3,56,158:14," +
"-1:2,158,-1:3,158,-1,158,-1:24,65,158:7,144,158:2,114,158:6,-1:2,158,-1:3,1" +
"58,-1,158,-1:24,158:15,68,158:2,-1:2,158,-1:3,158,-1,158,-1:24,158:8,70,158" +
":9,-1:2,158,-1:3,158,-1,158,-1:24,158,72,158:16,-1:2,158,-1:3,158,-1,158,-1" +
":24,158:5,73,158:12,-1:2,158,-1:3,158,-1,158,-1:24,68,158:17,-1:2,158,-1:3," +
"158,-1,158,-1:24,158:4,74,158:13,-1:2,158,-1:3,158,-1,158,-1:24,158:8,75,15" +
"8:9,-1:2,158,-1:3,158,-1,158,-1:24,158:9,76,158:8,-1:2,158,-1:3,158,-1,158," +
"-1:24,158:2,77,158:15,-1:2,158,-1:3,158,-1,158,-1:24,158:2,78,158:15,-1:2,1" +
"58,-1:3,158,-1,158,-1:24,158,75,158:16,-1:2,158,-1:3,158,-1,158,-1:24,158:3" +
",79,158:14,-1:2,158,-1:3,158,-1,158,-1:24,158,80,158:16,-1:2,158,-1:3,158,-" +
"1,158,-1:24,158:9,81,158:8,-1:2,158,-1:3,158,-1,158,-1:24,158:4,82,158:13,-" +
"1:2,158,-1:3,158,-1,158,-1:24,158:9,83,158:8,-1:2,158,-1:3,158,-1,158,-1:24" +
",158:9,84,158:8,-1:2,158,-1:3,158,-1,158,-1:24,158:7,85,158:10,-1:2,158,-1:" +
"3,158,-1,158,-1:24,158:3,128,158:5,86,158:8,-1:2,158,-1:3,158,-1,158,-1:24," +
"158:8,87,158:9,-1:2,158,-1:3,158,-1,158,-1:24,158:3,153,158:5,88,158:8,-1:2" +
",158,-1:3,158,-1,158,-1:24,158:3,89,158:14,-1:2,158,-1:3,158,-1,158,-1:24,1" +
"58:18,-1:2,90,-1:3,158,-1,158,-1:24,158:5,91,158:12,-1:2,158,-1:3,158,-1,15" +
"8,-1:24,158:6,92,158:11,-1:2,158,-1:3,158,-1,158,-1:24,158:4,93,158:13,-1:2" +
",158,-1:3,158,-1,158,-1:24,158:7,94,158:10,-1:2,158,-1:3,158,-1,158,-1:24,1" +
"58:4,95,158:13,-1:2,158,-1:3,158,-1,158,-1:24,158:8,96,158:9,-1:2,158,-1:3," +
"158,-1,158,-1:24,158:8,97,158:9,-1:2,158,-1:3,158,-1,158,-1:24,158:7,98,158" +
":10,-1:2,158,-1:3,158,-1,158,-1:24,158:13,99,158:4,-1:2,158,-1:3,158,-1,158" +
",-1:24,158:7,100,158:10,-1:2,158,-1:3,158,-1,158,-1:24,158:7,101,158:10,-1:" +
"2,158,-1:3,158,-1,158,-1:24,158:8,102,158:9,-1:2,158,-1:3,158,-1,158,-1:24," +
"158:15,103,158:2,-1:2,158,-1:3,158,-1,158,-1:24,158:6,104,158:11,-1:2,158,-" +
"1:3,158,-1,158,-1:24,158:3,109,158:14,-1:2,158,-1:3,158,-1,158,-1:24,158:9," +
"110,158:8,-1:2,158,-1:3,158,-1,158,-1:24,158:9,111,158:8,-1:2,158,-1:3,158," +
"-1,158,-1:24,158:7,112,158:10,-1:2,158,-1:3,158,-1,158,-1:24,158:2,155,158:" +
"5,113,158:9,-1:2,158,-1:3,158,-1,158,-1:24,158:3,115,158:14,-1:2,158,-1:3,1" +
"58,-1,158,-1:24,158:9,116,158:8,-1:2,158,-1:3,158,-1,158,-1:24,158:6,117,15" +
"8:11,-1:2,158,-1:3,158,-1,158,-1:24,158,118,158:16,-1:2,158,-1:3,158,-1,158" +
",-1:24,158:15,119,158:2,-1:2,158,-1:3,158,-1,158,-1:24,158:6,120,158:11,-1:" +
"2,158,-1:3,158,-1,158,-1:24,158:7,121,158:10,-1:2,158,-1:3,158,-1,158,-1:24" +
",158:2,122,158:15,-1:2,158,-1:3,158,-1,158,-1:24,158:4,123,158:13,-1:2,158," +
"-1:3,158,-1,158,-1:24,158:2,127,158:14,152,-1:2,158,-1:3,158,-1,158,-1:24,1" +
"58:7,129,158:10,-1:2,158,-1:3,158,-1,158,-1:24,158:2,130,158:15,-1:2,158,-1" +
":3,158,-1,158,-1:24,158:4,131,158:13,-1:2,158,-1:3,158,-1,158,-1:24,158:8,1" +
"32,158:9,-1:2,158,-1:3,158,-1,158,-1:24,158:6,133,158:11,-1:2,158,-1:3,158," +
"-1,158,-1:24,158:9,134,158:8,-1:2,158,-1:3,158,-1,158,-1:24,158:2,135,158:1" +
"5,-1:2,158,-1:3,158,-1,158,-1:24,158,136,158:16,-1:2,158,-1:3,158,-1,158,-1" +
":24,158:12,137,158:5,-1:2,158,-1:3,158,-1,158,-1:24,158:4,139,158:13,-1:2,1" +
"58,-1:3,158,-1,158,-1:24,158,140,158:16,-1:2,158,-1:3,158,-1,158,-1:24,158:" +
"5,141,158:12,-1:2,158,-1:3,158,-1,158,-1:24,158:10,142,158:7,-1:2,158,-1:3," +
"158,-1,158,-1:24,158:4,143,158:13,-1:2,158,-1:3,158,-1,158,-1:24,158:8,156," +
"158:9,-1:2,158,-1:3,158,-1,158,-1:24,158:4,145,158:13,-1:2,158,-1:3,158,-1," +
"158,-1:24,158:7,146,158:10,-1:2,158,-1:3,158,-1,158,-1:24,158:11,157,158:6," +
"-1:2,158,-1:3,158,-1,158,-1:24,158:4,147,158:13,-1:2,158,-1:3,158,-1,158,-1" +
":24,158,154,158:16,-1:2,158,-1:3,158,-1,158");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{System.out.println("Se recibio ="); return new Symbol(sym.ASIGNACION,yyline,yychar, yytext());}
					case -3:
						break;
					case 3:
						{System.out.println("Se recibio ;"); return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{System.out.println("Se recibio ,"); return new Symbol(sym.COMA,yyline,yychar, yytext());}
					case -5:
						break;
					case 5:
						{System.out.println("Se recibio ."); return new Symbol(sym.PUNTO,yyline,yychar, yytext());}
					case -6:
						break;
					case 6:
						{System.out.println("Se recibio :"); return new Symbol(sym.DOSPUNTOS,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{System.out.println("Se recibio )"); return new Symbol(sym.PARIZQ,yyline,yychar, yytext());}
					case -8:
						break;
					case 8:
						{System.out.println("Se recibio )"); return new Symbol(sym.PARDER,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{System.out.println("Se recibio ["); return new Symbol(sym.CORIZQ,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{System.out.println("Se recibio ]"); return new Symbol(sym.CORDER,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{System.out.println("Se recibio {"); return new Symbol(sym.LLAVIZQ,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{System.out.println("Se recibio }"); return new Symbol(sym.LLAVDER,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{System.out.println("Se recibio ?"); return new Symbol(sym.INTERROGACION,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{System.out.println("Se recibio +"); return new Symbol(sym.MAS,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{System.out.println("Se recibio -"); return new Symbol(sym.MENOS,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{System.out.println("Se recibio *"); return new Symbol(sym.POR,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{System.out.println("Se recibio /"); return new Symbol(sym.DIVIDIDO,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{System.out.println("Se recibio ^"); return new Symbol(sym.POTENCIA,yyline,yychar, yytext());}
					case -19:
						break;
					case 19:
						{System.out.println("Se recibio %"); return new Symbol(sym.MODULO,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{System.out.println("Se recibio <"); return new Symbol(sym.MENQUE,yyline,yychar, yytext());}
					case -21:
						break;
					case 21:
						{System.out.println("Se recibio >"); return new Symbol(sym.MAYQUE,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{System.out.println("Se recibio !"); return new Symbol(sym.NOT,yyline,yychar, yytext());}
					case -23:
						break;
					case 23:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -24:
						break;
					case 24:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -25:
						break;
					case 25:
						{yychar=1;}
					case -26:
						break;
					case 26:
						{}
					case -27:
						break;
					case 27:
						{System.out.println("Se recibio entero"); return new Symbol(sym.ENTERO,yyline,yychar, yytext());}
					case -28:
						break;
					case 28:
						{System.out.println("Se recibio =="); return new Symbol(sym.IGUAL,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{System.out.println("Se recibio ++"); return new Symbol(sym.INCREMENTO,yyline,yychar, yytext());}
					case -30:
						break;
					case 30:
						{System.out.println("Se recibio --"); return new Symbol(sym.DECREMENTO,yyline,yychar, yytext());}
					case -31:
						break;
					case 31:
						{System.out.println("Se recibio <="); return new Symbol(sym.MENIGUAL,yyline,yychar, yytext());}
					case -32:
						break;
					case 32:
						{System.out.println("Se recibio >="); return new Symbol(sym.MAYIGUAL,yyline,yychar, yytext());}
					case -33:
						break;
					case 33:
						{System.out.println("Se recibio !="); return new Symbol(sym.NOIGUAL,yyline,yychar, yytext());}
					case -34:
						break;
					case 34:
						{System.out.println("Se recibio ||"); return new Symbol(sym.OR,yyline,yychar, yytext());}
					case -35:
						break;
					case 35:
						{System.out.println("Se recibio &&"); return new Symbol(sym.AND,yyline,yychar, yytext());}
					case -36:
						break;
					case 36:
						{System.out.println("Se recibio pr si"); return new Symbol(sym.PRSI,yyline,yychar, yytext());}
					case -37:
						break;
					case 37:
						{System.out.println("Se recibio una cadena"); return new Symbol(sym.CADENA,yyline,yychar, yytext());}
					case -38:
						break;
					case 38:
						{System.out.println("Se recibio una caracter"); return new Symbol(sym.CARACTER,yyline,yychar, yytext());}
					case -39:
						break;
					case 39:
						{System.out.println("Se recibio un comentario"); }
					case -40:
						break;
					case 40:
						{System.out.println("Se recibio decimal"); return new Symbol(sym.DECIMAL,yyline,yychar, yytext());}
					case -41:
						break;
					case 41:
						{System.out.println("Se recibio un cmultilinea"); }
					case -42:
						break;
					case 42:
						{System.out.println("Se recibio pr sino"); return new Symbol(sym.PRSINO,yyline,yychar, yytext());}
					case -43:
						break;
					case 43:
						{System.out.println("Se recibio pr caso"); return new Symbol(sym.PRCASO,yyline,yychar, yytext());}
					case -44:
						break;
					case 44:
						{System.out.println("Se recibio un booleano"); return new Symbol(sym.BOOLEANO,yyline,yychar, yytext());}
					case -45:
						break;
					case 45:
						{System.out.println("Se recibio pr para"); return new Symbol(sym.PRPARA,yyline,yychar, yytext());}
					case -46:
						break;
					case 46:
						{System.out.println("Se recibio pr void"); return new Symbol(sym.PRVOID,yyline,yychar, yytext());}
					case -47:
						break;
					case 47:
						{System.out.println("Se recibio un tipo de dato"); return new Symbol(sym.TPDATO,yyline,yychar, yytext());}
					case -48:
						break;
					case 48:
						{System.out.println("Se recibio pr hacer"); return new Symbol(sym.PRHACER,yyline,yychar, yytext());}
					case -49:
						break;
					case 49:
						{System.out.println("Se recibio pr break"); return new Symbol(sym.PRCORTAR,yyline,yychar, yytext());}
					case -50:
						break;
					case 50:
						{System.out.println("Se recibio pr retorno"); return new Symbol(sym.PRRETORNO,yyline,yychar, yytext());}
					case -51:
						break;
					case 51:
						{System.out.println("Se recibio pr selector"); return new Symbol(sym.PRSELECTOR,yyline,yychar, yytext());}
					case -52:
						break;
					case 52:
						{System.out.println("Se recibio pr imprimir"); return new Symbol(sym.IMPRIMIR,yyline,yychar, yytext());}
					case -53:
						break;
					case 53:
						{System.out.println("Se recibio pr ejecutar"); return new Symbol(sym.PREJECUTAR,yyline,yychar, yytext());}
					case -54:
						break;
					case 54:
						{System.out.println("Se recibio pr mientras"); return new Symbol(sym.PRMIENTRAS,yyline,yychar, yytext());}
					case -55:
						break;
					case 55:
						{System.out.println("Se recibio pr continuar"); return new Symbol(sym.PRCONTINUAR,yyline,yychar, yytext());}
					case -56:
						break;
					case 56:
						{System.out.println("Se recibio pr default"); return new Symbol(sym.PRPORDEFECTO,yyline,yychar, yytext());}
					case -57:
						break;
					case 58:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -58:
						break;
					case 59:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -59:
						break;
					case 61:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -60:
						break;
					case 62:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -61:
						break;
					case 64:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -62:
						break;
					case 65:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -63:
						break;
					case 67:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -64:
						break;
					case 68:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -65:
						break;
					case 70:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -66:
						break;
					case 72:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -67:
						break;
					case 73:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -68:
						break;
					case 74:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -69:
						break;
					case 75:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -70:
						break;
					case 76:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -71:
						break;
					case 77:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -72:
						break;
					case 78:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -73:
						break;
					case 79:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -74:
						break;
					case 80:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -75:
						break;
					case 81:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -76:
						break;
					case 82:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -77:
						break;
					case 83:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -78:
						break;
					case 84:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -79:
						break;
					case 85:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -80:
						break;
					case 86:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -81:
						break;
					case 87:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -82:
						break;
					case 88:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -83:
						break;
					case 89:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -84:
						break;
					case 90:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -85:
						break;
					case 91:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -86:
						break;
					case 92:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -87:
						break;
					case 93:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -88:
						break;
					case 94:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -89:
						break;
					case 95:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -90:
						break;
					case 96:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -91:
						break;
					case 97:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -92:
						break;
					case 98:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -93:
						break;
					case 99:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -94:
						break;
					case 100:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -95:
						break;
					case 101:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -96:
						break;
					case 102:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -97:
						break;
					case 103:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -98:
						break;
					case 104:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -99:
						break;
					case 105:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -100:
						break;
					case 106:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -101:
						break;
					case 107:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -102:
						break;
					case 108:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -103:
						break;
					case 109:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -104:
						break;
					case 110:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -105:
						break;
					case 111:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -106:
						break;
					case 112:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -107:
						break;
					case 113:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -108:
						break;
					case 114:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -109:
						break;
					case 115:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -110:
						break;
					case 116:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -111:
						break;
					case 117:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -112:
						break;
					case 118:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -113:
						break;
					case 119:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -114:
						break;
					case 120:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -115:
						break;
					case 121:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -116:
						break;
					case 122:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -117:
						break;
					case 123:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -118:
						break;
					case 124:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -119:
						break;
					case 125:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -120:
						break;
					case 126:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -121:
						break;
					case 127:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -122:
						break;
					case 128:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -123:
						break;
					case 129:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -124:
						break;
					case 130:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -125:
						break;
					case 131:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -126:
						break;
					case 132:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -127:
						break;
					case 133:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -128:
						break;
					case 134:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -129:
						break;
					case 135:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -130:
						break;
					case 136:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -131:
						break;
					case 137:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -132:
						break;
					case 138:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -133:
						break;
					case 139:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -134:
						break;
					case 140:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -135:
						break;
					case 141:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -136:
						break;
					case 142:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -137:
						break;
					case 143:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -138:
						break;
					case 144:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -139:
						break;
					case 145:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -140:
						break;
					case 146:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -141:
						break;
					case 147:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -142:
						break;
					case 148:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -143:
						break;
					case 149:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -144:
						break;
					case 150:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -145:
						break;
					case 151:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -146:
						break;
					case 152:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -147:
						break;
					case 153:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -148:
						break;
					case 154:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -149:
						break;
					case 155:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -150:
						break;
					case 156:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -151:
						break;
					case 157:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -152:
						break;
					case 158:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -153:
						break;
					case 159:
						{System.out.println("Se recibio un identificador"); return new Symbol(sym.IDENTIFICADOR,yyline,yychar, yytext());}
					case -154:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
