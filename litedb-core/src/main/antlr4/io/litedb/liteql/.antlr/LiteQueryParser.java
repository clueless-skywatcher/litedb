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
		T__0=1, T__1=2, T__2=3, BOOLEAN_VALUE=4, AND=5, CREATE=6, DELETE=7, DROP=8, 
		FROM=9, IF=10, INSERT=11, INTO=12, SELECT=13, SET=14, TABLE=15, UPDATE=16, 
		VALUES=17, WHERE=18, WITH=19, EQ=20, NEQ=21, LT=22, LTE=23, GT=24, GTE=25, 
		PLUS=26, MINUS=27, ASTERISK=28, SLASH=29, PERCENT=30, CONCAT=31, SEMICOLON=32, 
		STRING=33, INTEGER=34, IDENTIFIER=35, QUOTED_IDENTIFIER=36, WS=37, COMMENT=38;
	public static final int
		RULE_root = 0, RULE_statement = 1, RULE_query = 2, RULE_dqlStatement = 3, 
		RULE_ddlStatement = 4, RULE_dmlStatement = 5, RULE_selectQuery = 6, RULE_filter = 7, 
		RULE_predicate = 8, RULE_createTableQuery = 9, RULE_dropTableQuery = 10, 
		RULE_insertQuery = 11, RULE_updateQuery = 12, RULE_deleteQuery = 13, RULE_updateColumn = 14, 
		RULE_value = 15, RULE_fieldNames = 16, RULE_fieldDefs = 17, RULE_fieldType = 18, 
		RULE_projection = 19, RULE_identifier = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "statement", "query", "dqlStatement", "ddlStatement", "dmlStatement", 
			"selectQuery", "filter", "predicate", "createTableQuery", "dropTableQuery", 
			"insertQuery", "updateQuery", "deleteQuery", "updateColumn", "value", 
			"fieldNames", "fieldDefs", "fieldType", "projection", "identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", null, "'and'", "'create'", "'delete'", "'drop'", 
			"'from'", "'if'", "'insert'", "'into'", "'select'", "'set'", "'table'", 
			"'update'", "'values'", "'where'", "'with'", "'='", null, "'<'", "'<='", 
			"'>'", "'>='", "'+'", "'-'", "'*'", "'/'", "'%'", "'||'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "BOOLEAN_VALUE", "AND", "CREATE", "DELETE", "DROP", 
			"FROM", "IF", "INSERT", "INTO", "SELECT", "SET", "TABLE", "UPDATE", "VALUES", 
			"WHERE", "WITH", "EQ", "NEQ", "LT", "LTE", "GT", "GTE", "PLUS", "MINUS", 
			"ASTERISK", "SLASH", "PERCENT", "CONCAT", "SEMICOLON", "STRING", "INTEGER", 
			"IDENTIFIER", "QUOTED_IDENTIFIER", "WS", "COMMENT"
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
			setState(42);
			statement();
			setState(43);
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
			setState(45);
			query();
			setState(46);
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
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				dqlStatement();
				}
				break;
			case CREATE:
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				ddlStatement();
				}
				break;
			case DELETE:
			case INSERT:
			case UPDATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
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
			setState(53);
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
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				createTableQuery();
				}
				break;
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
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
		public UpdateQueryContext updateQuery() {
			return getRuleContext(UpdateQueryContext.class,0);
		}
		public DeleteQueryContext deleteQuery() {
			return getRuleContext(DeleteQueryContext.class,0);
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
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INSERT:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				insertQuery();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				updateQuery();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				deleteQuery();
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
			setState(64);
			match(SELECT);
			setState(65);
			projection();
			setState(66);
			match(FROM);
			setState(67);
			((SelectQueryContext)_localctx).tableName = identifier();
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(68);
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
			setState(71);
			match(WHERE);
			setState(72);
			predicate();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(73);
				match(AND);
				setState(74);
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
			setState(77);
			((PredicateContext)_localctx).fieldName = identifier();
			setState(78);
			match(EQ);
			setState(79);
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
			setState(81);
			match(CREATE);
			setState(82);
			match(TABLE);
			setState(83);
			((CreateTableQueryContext)_localctx).tableName = identifier();
			setState(84);
			match(T__0);
			setState(85);
			fieldDefs();
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(86);
				match(T__1);
				setState(87);
				fieldDefs();
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(93);
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
			setState(95);
			match(DROP);
			setState(96);
			match(TABLE);
			setState(97);
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
			setState(99);
			match(INSERT);
			setState(100);
			match(INTO);
			setState(101);
			((InsertQueryContext)_localctx).tableName = identifier();
			setState(102);
			match(T__0);
			setState(103);
			fieldNames();
			setState(104);
			match(T__2);
			setState(105);
			match(VALUES);
			setState(106);
			match(T__0);
			setState(107);
			value();
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(108);
				match(T__1);
				setState(109);
				value();
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
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
	public static class UpdateQueryContext extends ParserRuleContext {
		public IdentifierContext tableName;
		public TerminalNode UPDATE() { return getToken(LiteQueryParser.UPDATE, 0); }
		public TerminalNode SET() { return getToken(LiteQueryParser.SET, 0); }
		public List<UpdateColumnContext> updateColumn() {
			return getRuleContexts(UpdateColumnContext.class);
		}
		public UpdateColumnContext updateColumn(int i) {
			return getRuleContext(UpdateColumnContext.class,i);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public UpdateQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateQuery; }
	}

	public final UpdateQueryContext updateQuery() throws RecognitionException {
		UpdateQueryContext _localctx = new UpdateQueryContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_updateQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(UPDATE);
			setState(118);
			((UpdateQueryContext)_localctx).tableName = identifier();
			setState(119);
			match(SET);
			setState(120);
			updateColumn();
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(121);
				match(T__1);
				setState(122);
				updateColumn();
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(128);
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
	public static class DeleteQueryContext extends ParserRuleContext {
		public IdentifierContext tableName;
		public TerminalNode DELETE() { return getToken(LiteQueryParser.DELETE, 0); }
		public TerminalNode FROM() { return getToken(LiteQueryParser.FROM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public DeleteQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteQuery; }
	}

	public final DeleteQueryContext deleteQuery() throws RecognitionException {
		DeleteQueryContext _localctx = new DeleteQueryContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_deleteQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(DELETE);
			setState(132);
			match(FROM);
			setState(133);
			((DeleteQueryContext)_localctx).tableName = identifier();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(134);
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
	public static class UpdateColumnContext extends ParserRuleContext {
		public IdentifierContext fieldName;
		public TerminalNode EQ() { return getToken(LiteQueryParser.EQ, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public UpdateColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateColumn; }
	}

	public final UpdateColumnContext updateColumn() throws RecognitionException {
		UpdateColumnContext _localctx = new UpdateColumnContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_updateColumn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			((UpdateColumnContext)_localctx).fieldName = identifier();
			setState(138);
			match(EQ);
			setState(139);
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
		enterRule(_localctx, 30, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 25769803792L) != 0)) ) {
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
		enterRule(_localctx, 32, RULE_fieldNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			identifier();
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(144);
				match(T__1);
				setState(145);
				identifier();
				}
				}
				setState(150);
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
		enterRule(_localctx, 34, RULE_fieldDefs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			((FieldDefsContext)_localctx).fieldName = identifier();
			setState(152);
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
		enterRule(_localctx, 36, RULE_fieldType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			((FieldTypeContext)_localctx).typeName = identifier();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(155);
				match(T__0);
				setState(156);
				((FieldTypeContext)_localctx).maxLength = match(INTEGER);
				setState(157);
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
		enterRule(_localctx, 38, RULE_projection);
		int _la;
		try {
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				identifier();
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(161);
					match(T__1);
					setState(162);
					identifier();
					}
					}
					setState(167);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case ASTERISK:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
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
		enterRule(_localctx, 40, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
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
		"\u0004\u0001&\u00ae\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u00024\b\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0003\u0004:\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005?\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006F\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007L\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\tY\b\t\n\t\f"+
		"\t\\\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000bo\b\u000b\n\u000b"+
		"\f\u000br\t\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0005\f|\b\f\n\f\f\f\u007f\t\f\u0001\f\u0003\f\u0082"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0088\b\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0005\u0010\u0093\b\u0010\n\u0010\f\u0010\u0096\t\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0003\u0012\u009f\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0005\u0013\u00a4\b\u0013\n\u0013\f\u0013\u00a7\t\u0013\u0001\u0013\u0003"+
		"\u0013\u00aa\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0000\u0000\u0015"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(\u0000\u0001\u0002\u0000\u0004\u0004!\"\u00a8\u0000"+
		"*\u0001\u0000\u0000\u0000\u0002-\u0001\u0000\u0000\u0000\u00043\u0001"+
		"\u0000\u0000\u0000\u00065\u0001\u0000\u0000\u0000\b9\u0001\u0000\u0000"+
		"\u0000\n>\u0001\u0000\u0000\u0000\f@\u0001\u0000\u0000\u0000\u000eG\u0001"+
		"\u0000\u0000\u0000\u0010M\u0001\u0000\u0000\u0000\u0012Q\u0001\u0000\u0000"+
		"\u0000\u0014_\u0001\u0000\u0000\u0000\u0016c\u0001\u0000\u0000\u0000\u0018"+
		"u\u0001\u0000\u0000\u0000\u001a\u0083\u0001\u0000\u0000\u0000\u001c\u0089"+
		"\u0001\u0000\u0000\u0000\u001e\u008d\u0001\u0000\u0000\u0000 \u008f\u0001"+
		"\u0000\u0000\u0000\"\u0097\u0001\u0000\u0000\u0000$\u009a\u0001\u0000"+
		"\u0000\u0000&\u00a9\u0001\u0000\u0000\u0000(\u00ab\u0001\u0000\u0000\u0000"+
		"*+\u0003\u0002\u0001\u0000+,\u0005\u0000\u0000\u0001,\u0001\u0001\u0000"+
		"\u0000\u0000-.\u0003\u0004\u0002\u0000./\u0005 \u0000\u0000/\u0003\u0001"+
		"\u0000\u0000\u000004\u0003\u0006\u0003\u000014\u0003\b\u0004\u000024\u0003"+
		"\n\u0005\u000030\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u000032\u0001"+
		"\u0000\u0000\u00004\u0005\u0001\u0000\u0000\u000056\u0003\f\u0006\u0000"+
		"6\u0007\u0001\u0000\u0000\u00007:\u0003\u0012\t\u00008:\u0003\u0014\n"+
		"\u000097\u0001\u0000\u0000\u000098\u0001\u0000\u0000\u0000:\t\u0001\u0000"+
		"\u0000\u0000;?\u0003\u0016\u000b\u0000<?\u0003\u0018\f\u0000=?\u0003\u001a"+
		"\r\u0000>;\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000>=\u0001\u0000"+
		"\u0000\u0000?\u000b\u0001\u0000\u0000\u0000@A\u0005\r\u0000\u0000AB\u0003"+
		"&\u0013\u0000BC\u0005\t\u0000\u0000CE\u0003(\u0014\u0000DF\u0003\u000e"+
		"\u0007\u0000ED\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000F\r\u0001"+
		"\u0000\u0000\u0000GH\u0005\u0012\u0000\u0000HK\u0003\u0010\b\u0000IJ\u0005"+
		"\u0005\u0000\u0000JL\u0003\u0010\b\u0000KI\u0001\u0000\u0000\u0000KL\u0001"+
		"\u0000\u0000\u0000L\u000f\u0001\u0000\u0000\u0000MN\u0003(\u0014\u0000"+
		"NO\u0005\u0014\u0000\u0000OP\u0003\u001e\u000f\u0000P\u0011\u0001\u0000"+
		"\u0000\u0000QR\u0005\u0006\u0000\u0000RS\u0005\u000f\u0000\u0000ST\u0003"+
		"(\u0014\u0000TU\u0005\u0001\u0000\u0000UZ\u0003\"\u0011\u0000VW\u0005"+
		"\u0002\u0000\u0000WY\u0003\"\u0011\u0000XV\u0001\u0000\u0000\u0000Y\\"+
		"\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000"+
		"\u0000[]\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000]^\u0005\u0003"+
		"\u0000\u0000^\u0013\u0001\u0000\u0000\u0000_`\u0005\b\u0000\u0000`a\u0005"+
		"\u000f\u0000\u0000ab\u0003(\u0014\u0000b\u0015\u0001\u0000\u0000\u0000"+
		"cd\u0005\u000b\u0000\u0000de\u0005\f\u0000\u0000ef\u0003(\u0014\u0000"+
		"fg\u0005\u0001\u0000\u0000gh\u0003 \u0010\u0000hi\u0005\u0003\u0000\u0000"+
		"ij\u0005\u0011\u0000\u0000jk\u0005\u0001\u0000\u0000kp\u0003\u001e\u000f"+
		"\u0000lm\u0005\u0002\u0000\u0000mo\u0003\u001e\u000f\u0000nl\u0001\u0000"+
		"\u0000\u0000or\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000pq\u0001"+
		"\u0000\u0000\u0000qs\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000"+
		"st\u0005\u0003\u0000\u0000t\u0017\u0001\u0000\u0000\u0000uv\u0005\u0010"+
		"\u0000\u0000vw\u0003(\u0014\u0000wx\u0005\u000e\u0000\u0000x}\u0003\u001c"+
		"\u000e\u0000yz\u0005\u0002\u0000\u0000z|\u0003\u001c\u000e\u0000{y\u0001"+
		"\u0000\u0000\u0000|\u007f\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000"+
		"\u0000}~\u0001\u0000\u0000\u0000~\u0081\u0001\u0000\u0000\u0000\u007f"+
		"}\u0001\u0000\u0000\u0000\u0080\u0082\u0003\u000e\u0007\u0000\u0081\u0080"+
		"\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0019"+
		"\u0001\u0000\u0000\u0000\u0083\u0084\u0005\u0007\u0000\u0000\u0084\u0085"+
		"\u0005\t\u0000\u0000\u0085\u0087\u0003(\u0014\u0000\u0086\u0088\u0003"+
		"\u000e\u0007\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0087\u0088\u0001"+
		"\u0000\u0000\u0000\u0088\u001b\u0001\u0000\u0000\u0000\u0089\u008a\u0003"+
		"(\u0014\u0000\u008a\u008b\u0005\u0014\u0000\u0000\u008b\u008c\u0003\u001e"+
		"\u000f\u0000\u008c\u001d\u0001\u0000\u0000\u0000\u008d\u008e\u0007\u0000"+
		"\u0000\u0000\u008e\u001f\u0001\u0000\u0000\u0000\u008f\u0094\u0003(\u0014"+
		"\u0000\u0090\u0091\u0005\u0002\u0000\u0000\u0091\u0093\u0003(\u0014\u0000"+
		"\u0092\u0090\u0001\u0000\u0000\u0000\u0093\u0096\u0001\u0000\u0000\u0000"+
		"\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000"+
		"\u0095!\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0003(\u0014\u0000\u0098\u0099\u0003$\u0012\u0000\u0099#\u0001"+
		"\u0000\u0000\u0000\u009a\u009e\u0003(\u0014\u0000\u009b\u009c\u0005\u0001"+
		"\u0000\u0000\u009c\u009d\u0005\"\u0000\u0000\u009d\u009f\u0005\u0003\u0000"+
		"\u0000\u009e\u009b\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000"+
		"\u0000\u009f%\u0001\u0000\u0000\u0000\u00a0\u00a5\u0003(\u0014\u0000\u00a1"+
		"\u00a2\u0005\u0002\u0000\u0000\u00a2\u00a4\u0003(\u0014\u0000\u00a3\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00aa"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00aa"+
		"\u0005\u001c\u0000\u0000\u00a9\u00a0\u0001\u0000\u0000\u0000\u00a9\u00a8"+
		"\u0001\u0000\u0000\u0000\u00aa\'\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005"+
		"#\u0000\u0000\u00ac)\u0001\u0000\u0000\u0000\u000e39>EKZp}\u0081\u0087"+
		"\u0094\u009e\u00a5\u00a9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}