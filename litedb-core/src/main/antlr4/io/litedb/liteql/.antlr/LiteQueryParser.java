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
		RULE_ddlStatement = 4, RULE_dmlStatement = 5, RULE_selectQuery = 6, RULE_tableNames = 7, 
		RULE_filter = 8, RULE_predicate = 9, RULE_createTableQuery = 10, RULE_dropTableQuery = 11, 
		RULE_insertQuery = 12, RULE_updateQuery = 13, RULE_deleteQuery = 14, RULE_updateColumn = 15, 
		RULE_value = 16, RULE_fieldNames = 17, RULE_fieldDefs = 18, RULE_fieldType = 19, 
		RULE_projection = 20, RULE_identifier = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "statement", "query", "dqlStatement", "ddlStatement", "dmlStatement", 
			"selectQuery", "tableNames", "filter", "predicate", "createTableQuery", 
			"dropTableQuery", "insertQuery", "updateQuery", "deleteQuery", "updateColumn", 
			"value", "fieldNames", "fieldDefs", "fieldType", "projection", "identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'('", "')'", null, "'and'", "'create'", "'delete'", "'drop'", 
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
			setState(44);
			statement();
			setState(45);
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
			setState(47);
			query();
			setState(48);
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
			setState(53);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				dqlStatement();
				}
				break;
			case CREATE:
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				ddlStatement();
				}
				break;
			case DELETE:
			case INSERT:
			case UPDATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
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
			setState(55);
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
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				createTableQuery();
				}
				break;
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
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
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INSERT:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				insertQuery();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				updateQuery();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
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
		public TerminalNode SELECT() { return getToken(LiteQueryParser.SELECT, 0); }
		public ProjectionContext projection() {
			return getRuleContext(ProjectionContext.class,0);
		}
		public TerminalNode FROM() { return getToken(LiteQueryParser.FROM, 0); }
		public TableNamesContext tableNames() {
			return getRuleContext(TableNamesContext.class,0);
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
			setState(66);
			match(SELECT);
			setState(67);
			projection();
			setState(68);
			match(FROM);
			setState(69);
			tableNames();
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(70);
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
	public static class TableNamesContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TableNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableNames; }
	}

	public final TableNamesContext tableNames() throws RecognitionException {
		TableNamesContext _localctx = new TableNamesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tableNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			identifier();
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(74);
				match(T__0);
				setState(75);
				identifier();
				}
				}
				setState(80);
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
		enterRule(_localctx, 16, RULE_filter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(WHERE);
			setState(82);
			predicate();
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(83);
				match(AND);
				setState(84);
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
		enterRule(_localctx, 18, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			((PredicateContext)_localctx).fieldName = identifier();
			setState(88);
			match(EQ);
			setState(89);
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
		enterRule(_localctx, 20, RULE_createTableQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(CREATE);
			setState(92);
			match(TABLE);
			setState(93);
			((CreateTableQueryContext)_localctx).tableName = identifier();
			setState(94);
			match(T__1);
			setState(95);
			fieldDefs();
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(96);
				match(T__0);
				setState(97);
				fieldDefs();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103);
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
		enterRule(_localctx, 22, RULE_dropTableQuery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(DROP);
			setState(106);
			match(TABLE);
			setState(107);
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
		enterRule(_localctx, 24, RULE_insertQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(INSERT);
			setState(110);
			match(INTO);
			setState(111);
			((InsertQueryContext)_localctx).tableName = identifier();
			setState(112);
			match(T__1);
			setState(113);
			fieldNames();
			setState(114);
			match(T__2);
			setState(115);
			match(VALUES);
			setState(116);
			match(T__1);
			setState(117);
			value();
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(118);
				match(T__0);
				setState(119);
				value();
				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(125);
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
		enterRule(_localctx, 26, RULE_updateQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(UPDATE);
			setState(128);
			((UpdateQueryContext)_localctx).tableName = identifier();
			setState(129);
			match(SET);
			setState(130);
			updateColumn();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(131);
				match(T__0);
				setState(132);
				updateColumn();
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(138);
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
		enterRule(_localctx, 28, RULE_deleteQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(DELETE);
			setState(142);
			match(FROM);
			setState(143);
			((DeleteQueryContext)_localctx).tableName = identifier();
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(144);
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
		enterRule(_localctx, 30, RULE_updateColumn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			((UpdateColumnContext)_localctx).fieldName = identifier();
			setState(148);
			match(EQ);
			setState(149);
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
		enterRule(_localctx, 32, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
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
		enterRule(_localctx, 34, RULE_fieldNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			identifier();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(154);
				match(T__0);
				setState(155);
				identifier();
				}
				}
				setState(160);
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
		enterRule(_localctx, 36, RULE_fieldDefs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			((FieldDefsContext)_localctx).fieldName = identifier();
			setState(162);
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
		enterRule(_localctx, 38, RULE_fieldType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			((FieldTypeContext)_localctx).typeName = identifier();
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(165);
				match(T__1);
				setState(166);
				((FieldTypeContext)_localctx).maxLength = match(INTEGER);
				setState(167);
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
		enterRule(_localctx, 40, RULE_projection);
		int _la;
		try {
			setState(179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				identifier();
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(171);
					match(T__0);
					setState(172);
					identifier();
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case ASTERISK:
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
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
		enterRule(_localctx, 42, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
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
		"\u0004\u0001&\u00b8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00026\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0003\u0004<\b\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005A\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006H\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007M\b\u0007\n\u0007\f\u0007P\t\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0003\bV\b\b\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005"+
		"\nc\b\n\n\n\f\nf\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0005\fy\b\f\n\f\f\f|\t\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u0086\b\r\n\r\f\r\u0089"+
		"\t\r\u0001\r\u0003\r\u008c\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u0092\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005"+
		"\u0011\u009d\b\u0011\n\u0011\f\u0011\u00a0\t\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u00a9\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u00ae\b"+
		"\u0014\n\u0014\f\u0014\u00b1\t\u0014\u0001\u0014\u0003\u0014\u00b4\b\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0000\u0000\u0016\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*\u0000\u0001\u0002\u0000\u0004\u0004!\"\u00b2\u0000,\u0001\u0000\u0000"+
		"\u0000\u0002/\u0001\u0000\u0000\u0000\u00045\u0001\u0000\u0000\u0000\u0006"+
		"7\u0001\u0000\u0000\u0000\b;\u0001\u0000\u0000\u0000\n@\u0001\u0000\u0000"+
		"\u0000\fB\u0001\u0000\u0000\u0000\u000eI\u0001\u0000\u0000\u0000\u0010"+
		"Q\u0001\u0000\u0000\u0000\u0012W\u0001\u0000\u0000\u0000\u0014[\u0001"+
		"\u0000\u0000\u0000\u0016i\u0001\u0000\u0000\u0000\u0018m\u0001\u0000\u0000"+
		"\u0000\u001a\u007f\u0001\u0000\u0000\u0000\u001c\u008d\u0001\u0000\u0000"+
		"\u0000\u001e\u0093\u0001\u0000\u0000\u0000 \u0097\u0001\u0000\u0000\u0000"+
		"\"\u0099\u0001\u0000\u0000\u0000$\u00a1\u0001\u0000\u0000\u0000&\u00a4"+
		"\u0001\u0000\u0000\u0000(\u00b3\u0001\u0000\u0000\u0000*\u00b5\u0001\u0000"+
		"\u0000\u0000,-\u0003\u0002\u0001\u0000-.\u0005\u0000\u0000\u0001.\u0001"+
		"\u0001\u0000\u0000\u0000/0\u0003\u0004\u0002\u000001\u0005 \u0000\u0000"+
		"1\u0003\u0001\u0000\u0000\u000026\u0003\u0006\u0003\u000036\u0003\b\u0004"+
		"\u000046\u0003\n\u0005\u000052\u0001\u0000\u0000\u000053\u0001\u0000\u0000"+
		"\u000054\u0001\u0000\u0000\u00006\u0005\u0001\u0000\u0000\u000078\u0003"+
		"\f\u0006\u00008\u0007\u0001\u0000\u0000\u00009<\u0003\u0014\n\u0000:<"+
		"\u0003\u0016\u000b\u0000;9\u0001\u0000\u0000\u0000;:\u0001\u0000\u0000"+
		"\u0000<\t\u0001\u0000\u0000\u0000=A\u0003\u0018\f\u0000>A\u0003\u001a"+
		"\r\u0000?A\u0003\u001c\u000e\u0000@=\u0001\u0000\u0000\u0000@>\u0001\u0000"+
		"\u0000\u0000@?\u0001\u0000\u0000\u0000A\u000b\u0001\u0000\u0000\u0000"+
		"BC\u0005\r\u0000\u0000CD\u0003(\u0014\u0000DE\u0005\t\u0000\u0000EG\u0003"+
		"\u000e\u0007\u0000FH\u0003\u0010\b\u0000GF\u0001\u0000\u0000\u0000GH\u0001"+
		"\u0000\u0000\u0000H\r\u0001\u0000\u0000\u0000IN\u0003*\u0015\u0000JK\u0005"+
		"\u0001\u0000\u0000KM\u0003*\u0015\u0000LJ\u0001\u0000\u0000\u0000MP\u0001"+
		"\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000"+
		"O\u000f\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000QR\u0005\u0012"+
		"\u0000\u0000RU\u0003\u0012\t\u0000ST\u0005\u0005\u0000\u0000TV\u0003\u0012"+
		"\t\u0000US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000V\u0011\u0001"+
		"\u0000\u0000\u0000WX\u0003*\u0015\u0000XY\u0005\u0014\u0000\u0000YZ\u0003"+
		" \u0010\u0000Z\u0013\u0001\u0000\u0000\u0000[\\\u0005\u0006\u0000\u0000"+
		"\\]\u0005\u000f\u0000\u0000]^\u0003*\u0015\u0000^_\u0005\u0002\u0000\u0000"+
		"_d\u0003$\u0012\u0000`a\u0005\u0001\u0000\u0000ac\u0003$\u0012\u0000b"+
		"`\u0001\u0000\u0000\u0000cf\u0001\u0000\u0000\u0000db\u0001\u0000\u0000"+
		"\u0000de\u0001\u0000\u0000\u0000eg\u0001\u0000\u0000\u0000fd\u0001\u0000"+
		"\u0000\u0000gh\u0005\u0003\u0000\u0000h\u0015\u0001\u0000\u0000\u0000"+
		"ij\u0005\b\u0000\u0000jk\u0005\u000f\u0000\u0000kl\u0003*\u0015\u0000"+
		"l\u0017\u0001\u0000\u0000\u0000mn\u0005\u000b\u0000\u0000no\u0005\f\u0000"+
		"\u0000op\u0003*\u0015\u0000pq\u0005\u0002\u0000\u0000qr\u0003\"\u0011"+
		"\u0000rs\u0005\u0003\u0000\u0000st\u0005\u0011\u0000\u0000tu\u0005\u0002"+
		"\u0000\u0000uz\u0003 \u0010\u0000vw\u0005\u0001\u0000\u0000wy\u0003 \u0010"+
		"\u0000xv\u0001\u0000\u0000\u0000y|\u0001\u0000\u0000\u0000zx\u0001\u0000"+
		"\u0000\u0000z{\u0001\u0000\u0000\u0000{}\u0001\u0000\u0000\u0000|z\u0001"+
		"\u0000\u0000\u0000}~\u0005\u0003\u0000\u0000~\u0019\u0001\u0000\u0000"+
		"\u0000\u007f\u0080\u0005\u0010\u0000\u0000\u0080\u0081\u0003*\u0015\u0000"+
		"\u0081\u0082\u0005\u000e\u0000\u0000\u0082\u0087\u0003\u001e\u000f\u0000"+
		"\u0083\u0084\u0005\u0001\u0000\u0000\u0084\u0086\u0003\u001e\u000f\u0000"+
		"\u0085\u0083\u0001\u0000\u0000\u0000\u0086\u0089\u0001\u0000\u0000\u0000"+
		"\u0087\u0085\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000"+
		"\u0088\u008b\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000\u0000"+
		"\u008a\u008c\u0003\u0010\b\u0000\u008b\u008a\u0001\u0000\u0000\u0000\u008b"+
		"\u008c\u0001\u0000\u0000\u0000\u008c\u001b\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0005\u0007\u0000\u0000\u008e\u008f\u0005\t\u0000\u0000\u008f\u0091"+
		"\u0003*\u0015\u0000\u0090\u0092\u0003\u0010\b\u0000\u0091\u0090\u0001"+
		"\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u001d\u0001"+
		"\u0000\u0000\u0000\u0093\u0094\u0003*\u0015\u0000\u0094\u0095\u0005\u0014"+
		"\u0000\u0000\u0095\u0096\u0003 \u0010\u0000\u0096\u001f\u0001\u0000\u0000"+
		"\u0000\u0097\u0098\u0007\u0000\u0000\u0000\u0098!\u0001\u0000\u0000\u0000"+
		"\u0099\u009e\u0003*\u0015\u0000\u009a\u009b\u0005\u0001\u0000\u0000\u009b"+
		"\u009d\u0003*\u0015\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009d\u00a0"+
		"\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009f"+
		"\u0001\u0000\u0000\u0000\u009f#\u0001\u0000\u0000\u0000\u00a0\u009e\u0001"+
		"\u0000\u0000\u0000\u00a1\u00a2\u0003*\u0015\u0000\u00a2\u00a3\u0003&\u0013"+
		"\u0000\u00a3%\u0001\u0000\u0000\u0000\u00a4\u00a8\u0003*\u0015\u0000\u00a5"+
		"\u00a6\u0005\u0002\u0000\u0000\u00a6\u00a7\u0005\"\u0000\u0000\u00a7\u00a9"+
		"\u0005\u0003\u0000\u0000\u00a8\u00a5\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a9\'\u0001\u0000\u0000\u0000\u00aa\u00af\u0003"+
		"*\u0015\u0000\u00ab\u00ac\u0005\u0001\u0000\u0000\u00ac\u00ae\u0003*\u0015"+
		"\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00b1\u0001\u0000\u0000"+
		"\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b4\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b4\u0005\u001c\u0000\u0000\u00b3\u00aa\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b4)\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b6\u0005#\u0000\u0000\u00b6+\u0001\u0000\u0000\u0000\u000f5"+
		";@GNUdz\u0087\u008b\u0091\u009e\u00a8\u00af\u00b3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}