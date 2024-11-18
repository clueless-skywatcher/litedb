// Generated from /home/epsilonator/GitHub/litedb/litedb-core/src/main/antlr4/io/litedb/liteql/LiteQuery.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LiteQueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, BOOLEAN_VALUE=4, AND=5, CREATE=6, DROP=7, FROM=8, 
		IF=9, INSERT=10, INTO=11, SELECT=12, TABLE=13, VALUES=14, WHERE=15, WITH=16, 
		EQ=17, NEQ=18, LT=19, LTE=20, GT=21, GTE=22, PLUS=23, MINUS=24, ASTERISK=25, 
		SLASH=26, PERCENT=27, CONCAT=28, SEMICOLON=29, STRING=30, INTEGER=31, 
		IDENTIFIER=32, QUOTED_IDENTIFIER=33, WS=34, COMMENT=35;
	public static final int
		RULE_root = 0, RULE_statement = 1, RULE_query = 2, RULE_dqlStatement = 3, 
		RULE_ddlStatement = 4, RULE_dmlStatement = 5, RULE_selectQuery = 6, RULE_filter = 7, 
		RULE_predicate = 8, RULE_createTableQuery = 9, RULE_dropTableQuery = 10, 
		RULE_insertQuery = 11, RULE_value = 12, RULE_fieldNames = 13, RULE_fieldDefs = 14, 
		RULE_fieldType = 15, RULE_projection = 16, RULE_identifier = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "statement", "query", "dqlStatement", "ddlStatement", "dmlStatement", 
			"selectQuery", "filter", "predicate", "createTableQuery", "dropTableQuery", 
			"insertQuery", "value", "fieldNames", "fieldDefs", "fieldType", "projection", 
			"identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", null, "'and'", "'create'", "'drop'", "'from'", 
			"'if'", "'insert'", "'into'", "'select'", "'table'", "'values'", "'where'", 
			"'with'", "'='", null, "'<'", "'<='", "'>'", "'>='", "'+'", "'-'", "'*'", 
			"'/'", "'%'", "'||'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "BOOLEAN_VALUE", "AND", "CREATE", "DROP", "FROM", 
			"IF", "INSERT", "INTO", "SELECT", "TABLE", "VALUES", "WHERE", "WITH", 
			"EQ", "NEQ", "LT", "LTE", "GT", "GTE", "PLUS", "MINUS", "ASTERISK", "SLASH", 
			"PERCENT", "CONCAT", "SEMICOLON", "STRING", "INTEGER", "IDENTIFIER", 
			"QUOTED_IDENTIFIER", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "LiteQuery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LiteQueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RootContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode EOF() { return getToken(LiteQueryParser.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			statement();
			setState(37);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(LiteQueryParser.SEMICOLON, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			query();
			setState(40);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QueryContext extends ParserRuleContext {
		public DqlStatementContext dqlStatement() {
			return getRuleContext(DqlStatementContext.class,0);
		}
		public DdlStatementContext ddlStatement() {
			return getRuleContext(DdlStatementContext.class,0);
		}
		public DmlStatementContext dmlStatement() {
			return getRuleContext(DmlStatementContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_query);
		try {
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				dqlStatement();
				}
				break;
			case CREATE:
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				ddlStatement();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 3);
				{
				setState(44);
				dmlStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DqlStatementContext extends ParserRuleContext {
		public SelectQueryContext selectQuery() {
			return getRuleContext(SelectQueryContext.class,0);
		}
		public DqlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dqlStatement; }
	}

	public final DqlStatementContext dqlStatement() throws RecognitionException {
		DqlStatementContext _localctx = new DqlStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dqlStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			selectQuery();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DdlStatementContext extends ParserRuleContext {
		public CreateTableQueryContext createTableQuery() {
			return getRuleContext(CreateTableQueryContext.class,0);
		}
		public DropTableQueryContext dropTableQuery() {
			return getRuleContext(DropTableQueryContext.class,0);
		}
		public DdlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ddlStatement; }
	}

	public final DdlStatementContext ddlStatement() throws RecognitionException {
		DdlStatementContext _localctx = new DdlStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ddlStatement);
		try {
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				createTableQuery();
				}
				break;
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				dropTableQuery();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DmlStatementContext extends ParserRuleContext {
		public InsertQueryContext insertQuery() {
			return getRuleContext(InsertQueryContext.class,0);
		}
		public DmlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dmlStatement; }
	}

	public final DmlStatementContext dmlStatement() throws RecognitionException {
		DmlStatementContext _localctx = new DmlStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_dmlStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			insertQuery();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectQueryContext extends ParserRuleContext {
		public IdentifierContext tableName;
		public TerminalNode SELECT() { return getToken(LiteQueryParser.SELECT, 0); }
		public ProjectionContext projection() {
			return getRuleContext(ProjectionContext.class,0);
		}
		public TerminalNode FROM() { return getToken(LiteQueryParser.FROM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public SelectQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectQuery; }
	}

	public final SelectQueryContext selectQuery() throws RecognitionException {
		SelectQueryContext _localctx = new SelectQueryContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_selectQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(SELECT);
			setState(56);
			projection();
			setState(57);
			match(FROM);
			setState(58);
			((SelectQueryContext)_localctx).tableName = identifier();
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(59);
				filter();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FilterContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(LiteQueryParser.WHERE, 0); }
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public TerminalNode AND() { return getToken(LiteQueryParser.AND, 0); }
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_filter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(WHERE);
			setState(63);
			predicate();
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(64);
				match(AND);
				setState(65);
				predicate();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicateContext extends ParserRuleContext {
		public IdentifierContext fieldName;
		public TerminalNode EQ() { return getToken(LiteQueryParser.EQ, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			((PredicateContext)_localctx).fieldName = identifier();
			setState(69);
			match(EQ);
			setState(70);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateTableQueryContext extends ParserRuleContext {
		public IdentifierContext tableName;
		public TerminalNode CREATE() { return getToken(LiteQueryParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(LiteQueryParser.TABLE, 0); }
		public List<FieldDefsContext> fieldDefs() {
			return getRuleContexts(FieldDefsContext.class);
		}
		public FieldDefsContext fieldDefs(int i) {
			return getRuleContext(FieldDefsContext.class,i);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public CreateTableQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTableQuery; }
	}

	public final CreateTableQueryContext createTableQuery() throws RecognitionException {
		CreateTableQueryContext _localctx = new CreateTableQueryContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_createTableQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(CREATE);
			setState(73);
			match(TABLE);
			setState(74);
			((CreateTableQueryContext)_localctx).tableName = identifier();
			setState(75);
			match(T__0);
			setState(76);
			fieldDefs();
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(77);
				match(T__1);
				setState(78);
				fieldDefs();
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(84);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DropTableQueryContext extends ParserRuleContext {
		public IdentifierContext tableName;
		public TerminalNode DROP() { return getToken(LiteQueryParser.DROP, 0); }
		public TerminalNode TABLE() { return getToken(LiteQueryParser.TABLE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public DropTableQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropTableQuery; }
	}

	public final DropTableQueryContext dropTableQuery() throws RecognitionException {
		DropTableQueryContext _localctx = new DropTableQueryContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_dropTableQuery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(DROP);
			setState(87);
			match(TABLE);
			setState(88);
			((DropTableQueryContext)_localctx).tableName = identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InsertQueryContext extends ParserRuleContext {
		public IdentifierContext tableName;
		public TerminalNode INSERT() { return getToken(LiteQueryParser.INSERT, 0); }
		public TerminalNode INTO() { return getToken(LiteQueryParser.INTO, 0); }
		public FieldNamesContext fieldNames() {
			return getRuleContext(FieldNamesContext.class,0);
		}
		public TerminalNode VALUES() { return getToken(LiteQueryParser.VALUES, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public InsertQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertQuery; }
	}

	public final InsertQueryContext insertQuery() throws RecognitionException {
		InsertQueryContext _localctx = new InsertQueryContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_insertQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(INSERT);
			setState(91);
			match(INTO);
			setState(92);
			((InsertQueryContext)_localctx).tableName = identifier();
			setState(93);
			match(T__0);
			setState(94);
			fieldNames();
			setState(95);
			match(T__2);
			setState(96);
			match(VALUES);
			setState(97);
			match(T__0);
			setState(98);
			value();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(99);
				match(T__1);
				setState(100);
				value();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(106);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(LiteQueryParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(LiteQueryParser.STRING, 0); }
		public TerminalNode BOOLEAN_VALUE() { return getToken(LiteQueryParser.BOOLEAN_VALUE, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3221225488L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldNamesContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public FieldNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldNames; }
	}

	public final FieldNamesContext fieldNames() throws RecognitionException {
		FieldNamesContext _localctx = new FieldNamesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_fieldNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			identifier();
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(111);
				match(T__1);
				setState(112);
				identifier();
				}
				}
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldDefsContext extends ParserRuleContext {
		public IdentifierContext fieldName;
		public FieldTypeContext fieldType() {
			return getRuleContext(FieldTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FieldDefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDefs; }
	}

	public final FieldDefsContext fieldDefs() throws RecognitionException {
		FieldDefsContext _localctx = new FieldDefsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_fieldDefs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			((FieldDefsContext)_localctx).fieldName = identifier();
			setState(119);
			fieldType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldTypeContext extends ParserRuleContext {
		public IdentifierContext typeName;
		public Token maxLength;
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(LiteQueryParser.INTEGER, 0); }
		public FieldTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldType; }
	}

	public final FieldTypeContext fieldType() throws RecognitionException {
		FieldTypeContext _localctx = new FieldTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_fieldType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			((FieldTypeContext)_localctx).typeName = identifier();
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(122);
				match(T__0);
				setState(123);
				((FieldTypeContext)_localctx).maxLength = match(INTEGER);
				setState(124);
				match(T__2);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProjectionContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode ASTERISK() { return getToken(LiteQueryParser.ASTERISK, 0); }
		public ProjectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projection; }
	}

	public final ProjectionContext projection() throws RecognitionException {
		ProjectionContext _localctx = new ProjectionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_projection);
		int _la;
		try {
			setState(136);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				identifier();
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(128);
					match(T__1);
					setState(129);
					identifier();
					}
					}
					setState(134);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case ASTERISK:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				match(ASTERISK);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LiteQueryParser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001#\u008d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002.\b\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0003\u00044\b\u0004\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006=\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007C\b\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0005\tP\b\t\n\t\f\tS\t\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0005\u000bf\b\u000b\n\u000b\f\u000bi\t\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0005\rr\b\r\n\r\f\ru\t"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f~\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0005\u0010\u0083\b\u0010\n\u0010\f\u0010\u0086\t\u0010\u0001\u0010\u0003"+
		"\u0010\u0089\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0000\u0000\u0012"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"\u0000\u0001\u0002\u0000\u0004\u0004\u001e\u001f\u0085"+
		"\u0000$\u0001\u0000\u0000\u0000\u0002\'\u0001\u0000\u0000\u0000\u0004"+
		"-\u0001\u0000\u0000\u0000\u0006/\u0001\u0000\u0000\u0000\b3\u0001\u0000"+
		"\u0000\u0000\n5\u0001\u0000\u0000\u0000\f7\u0001\u0000\u0000\u0000\u000e"+
		">\u0001\u0000\u0000\u0000\u0010D\u0001\u0000\u0000\u0000\u0012H\u0001"+
		"\u0000\u0000\u0000\u0014V\u0001\u0000\u0000\u0000\u0016Z\u0001\u0000\u0000"+
		"\u0000\u0018l\u0001\u0000\u0000\u0000\u001an\u0001\u0000\u0000\u0000\u001c"+
		"v\u0001\u0000\u0000\u0000\u001ey\u0001\u0000\u0000\u0000 \u0088\u0001"+
		"\u0000\u0000\u0000\"\u008a\u0001\u0000\u0000\u0000$%\u0003\u0002\u0001"+
		"\u0000%&\u0005\u0000\u0000\u0001&\u0001\u0001\u0000\u0000\u0000\'(\u0003"+
		"\u0004\u0002\u0000()\u0005\u001d\u0000\u0000)\u0003\u0001\u0000\u0000"+
		"\u0000*.\u0003\u0006\u0003\u0000+.\u0003\b\u0004\u0000,.\u0003\n\u0005"+
		"\u0000-*\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000-,\u0001\u0000"+
		"\u0000\u0000.\u0005\u0001\u0000\u0000\u0000/0\u0003\f\u0006\u00000\u0007"+
		"\u0001\u0000\u0000\u000014\u0003\u0012\t\u000024\u0003\u0014\n\u00003"+
		"1\u0001\u0000\u0000\u000032\u0001\u0000\u0000\u00004\t\u0001\u0000\u0000"+
		"\u000056\u0003\u0016\u000b\u00006\u000b\u0001\u0000\u0000\u000078\u0005"+
		"\f\u0000\u000089\u0003 \u0010\u00009:\u0005\b\u0000\u0000:<\u0003\"\u0011"+
		"\u0000;=\u0003\u000e\u0007\u0000<;\u0001\u0000\u0000\u0000<=\u0001\u0000"+
		"\u0000\u0000=\r\u0001\u0000\u0000\u0000>?\u0005\u000f\u0000\u0000?B\u0003"+
		"\u0010\b\u0000@A\u0005\u0005\u0000\u0000AC\u0003\u0010\b\u0000B@\u0001"+
		"\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000C\u000f\u0001\u0000\u0000"+
		"\u0000DE\u0003\"\u0011\u0000EF\u0005\u0011\u0000\u0000FG\u0003\u0018\f"+
		"\u0000G\u0011\u0001\u0000\u0000\u0000HI\u0005\u0006\u0000\u0000IJ\u0005"+
		"\r\u0000\u0000JK\u0003\"\u0011\u0000KL\u0005\u0001\u0000\u0000LQ\u0003"+
		"\u001c\u000e\u0000MN\u0005\u0002\u0000\u0000NP\u0003\u001c\u000e\u0000"+
		"OM\u0001\u0000\u0000\u0000PS\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000"+
		"\u0000QR\u0001\u0000\u0000\u0000RT\u0001\u0000\u0000\u0000SQ\u0001\u0000"+
		"\u0000\u0000TU\u0005\u0003\u0000\u0000U\u0013\u0001\u0000\u0000\u0000"+
		"VW\u0005\u0007\u0000\u0000WX\u0005\r\u0000\u0000XY\u0003\"\u0011\u0000"+
		"Y\u0015\u0001\u0000\u0000\u0000Z[\u0005\n\u0000\u0000[\\\u0005\u000b\u0000"+
		"\u0000\\]\u0003\"\u0011\u0000]^\u0005\u0001\u0000\u0000^_\u0003\u001a"+
		"\r\u0000_`\u0005\u0003\u0000\u0000`a\u0005\u000e\u0000\u0000ab\u0005\u0001"+
		"\u0000\u0000bg\u0003\u0018\f\u0000cd\u0005\u0002\u0000\u0000df\u0003\u0018"+
		"\f\u0000ec\u0001\u0000\u0000\u0000fi\u0001\u0000\u0000\u0000ge\u0001\u0000"+
		"\u0000\u0000gh\u0001\u0000\u0000\u0000hj\u0001\u0000\u0000\u0000ig\u0001"+
		"\u0000\u0000\u0000jk\u0005\u0003\u0000\u0000k\u0017\u0001\u0000\u0000"+
		"\u0000lm\u0007\u0000\u0000\u0000m\u0019\u0001\u0000\u0000\u0000ns\u0003"+
		"\"\u0011\u0000op\u0005\u0002\u0000\u0000pr\u0003\"\u0011\u0000qo\u0001"+
		"\u0000\u0000\u0000ru\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000"+
		"st\u0001\u0000\u0000\u0000t\u001b\u0001\u0000\u0000\u0000us\u0001\u0000"+
		"\u0000\u0000vw\u0003\"\u0011\u0000wx\u0003\u001e\u000f\u0000x\u001d\u0001"+
		"\u0000\u0000\u0000y}\u0003\"\u0011\u0000z{\u0005\u0001\u0000\u0000{|\u0005"+
		"\u001f\u0000\u0000|~\u0005\u0003\u0000\u0000}z\u0001\u0000\u0000\u0000"+
		"}~\u0001\u0000\u0000\u0000~\u001f\u0001\u0000\u0000\u0000\u007f\u0084"+
		"\u0003\"\u0011\u0000\u0080\u0081\u0005\u0002\u0000\u0000\u0081\u0083\u0003"+
		"\"\u0011\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0083\u0086\u0001\u0000"+
		"\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0089\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000"+
		"\u0000\u0000\u0087\u0089\u0005\u0019\u0000\u0000\u0088\u007f\u0001\u0000"+
		"\u0000\u0000\u0088\u0087\u0001\u0000\u0000\u0000\u0089!\u0001\u0000\u0000"+
		"\u0000\u008a\u008b\u0005 \u0000\u0000\u008b#\u0001\u0000\u0000\u0000\n"+
		"-3<BQgs}\u0084\u0088";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}