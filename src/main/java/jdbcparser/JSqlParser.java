package jdbcparser;

import com.google.common.collect.Lists;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ComparisonOperator;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;
import net.sf.jsqlparser.util.deparser.SelectDeParser;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class JSqlParser {

    public static void main(String[] args) {

        try {
            String sql = "SELECT * FROM TABLE1 a where a.userid = 123";
//方法1
            Statement statement = CCJSqlParserUtil.parse(sql);

            Select selectStatement = (Select) statement;
            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
            List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
            System.out.println(tableList);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testWhere() {

        try {
            String sql = "SELECT * FROM TABLE1 where userid = 123";
//方法1
            String whereClause = "a=3 AND b=4 AND c=5 AND d>5 AND x<10";
            Expression expr = CCJSqlParserUtil.parseCondExpression(whereClause);
            expr.accept(new ExpressionVisitorAdapter() {

                @Override
                protected void visitBinaryExpression(BinaryExpression expr) {
                    if (expr instanceof ComparisonOperator) {
                        System.out.println("left=" + expr.getLeftExpression() + "  op=" +  expr.getStringExpression() + "  right=" + expr.getRightExpression());
                    }

                    super.visitBinaryExpression(expr);
                }
            });
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetWhereFromSql() {

        try {
            // TODO Auto-generated method stub
            String statement = "SELECT a,b,c FROM mytable WHERE mytable.col = 9 and b=c LIMIT 3, ?";
//statement = SampleSQL1.sql;

            CCJSqlParserManager parserManager = new CCJSqlParserManager();
            Select select = (Select) parserManager.parse(new StringReader(statement));

            PlainSelect ps = (PlainSelect) select.getSelectBody();

            System.out.println(ps.getWhere().toString());
            System.out.println(ps.getSelectItems().get(1).toString());

            // here you have to check what kind of expression it is and execute your actions individualy for every expression implementation
            AndExpression e = (AndExpression) ps.getWhere();
            System.out.println(e.getLeftExpression());
        } catch (JSQLParserException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void testSubQuery() {

            String sql = "SELECT * FROM myTable, (select * from myTable2) as data1, (select b from myTable3) as data2";
        Select select = null;
        try {
            select = (Select) CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }


        System.out.println(select.toString());


            System.out.println("Type 1: Visitor processing");
            select.getSelectBody().accept(new SelectVisitorAdapter(){
                @Override
                public void visit(PlainSelect plainSelect) {
                    plainSelect.getFromItem().accept(fromVisitor);
                    if (plainSelect.getJoins()!=null)
                        plainSelect.getJoins().forEach(join -> join.getRightItem().accept(fromVisitor));
                }
            });

            System.out.println("Type 2: simple method calls");
            processFromItem(((PlainSelect)select.getSelectBody()).getFromItem());
            if (((PlainSelect)select.getSelectBody()).getJoins()!=null)
                ((PlainSelect)select.getSelectBody()).getJoins().forEach(join -> processFromItem(join.getRightItem()));


            System.out.println("Type 3: hierarchically process all subselects");
            select.getSelectBody().accept(new SelectDeParser() {
                @Override
                public void visit(SubSelect subSelect) {
                    System.out.println("  found subselect=" + subSelect.toString());
                    super.visit(subSelect);             }
            });


    }


    @Test
    public void testDisectSql() {

        try {
            // TODO Auto-generated method stub
            String statement = "SELECT a.orderId,a.phone,b.address,b.email FROM myTable a, otherTable b where a.userId = b.orderId and a.userId = 12345";

            CCJSqlParserManager parserManager = new CCJSqlParserManager();
            Select select = (Select) parserManager.parse(new StringReader(statement));

            PlainSelect ps = (PlainSelect) select.getSelectBody();

            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();

            System.out.println("tables-->" +  tablesNamesFinder.getTableList(select));
            System.out.println("where-->" + ps.getWhere().toString());
            System.out.println("SelectItem-->" + ps.getSelectItems().get(0).toString());

            // here you have to check what kind of expression it is and execute your actions individualy for every expression implementation
            AndExpression e = (AndExpression) ps.getWhere();
            System.out.println(e.getLeftExpression());
            System.out.println(e.getRightExpression());
        } catch (JSQLParserException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void testGetSelectItem() {

        try {
            // TODO Auto-generated method stub
            String statement = "SELECT a.orderId as orderId,a.phone,b.address,b.email FROM myTable a, otherTable b where a.userId = b.orderId and a.userId = 12345";

            CCJSqlParserManager parserManager = new CCJSqlParserManager();
            Select select = (Select) parserManager.parse(new StringReader(statement));

            PlainSelect ps = (PlainSelect) select.getSelectBody();

            List<SelectItem> selectItems = ps.getSelectItems();

            List<String> selectPrams = new ArrayList<>();

            if (!CollectionUtils.isEmpty(selectItems)) {
                selectItems.stream().forEach(selectItem -> {

                     selectItem.accept(new SelectItemVisitor() {
                        @Override
                        public void visit(AllColumns allColumns) {

                        }

                        @Override
                        public void visit(AllTableColumns allTableColumns) {

                        }

                        @Override
                        public void visit(SelectExpressionItem selectExpressionItem) {
                            String columnName = null;
                            //先判断alias 是否为null
                            Alias alias = selectExpressionItem.getAlias();
                            if (alias != null) {

                                 columnName = selectExpressionItem.getAlias().getName();
                            }
                            if (StringUtils.isEmpty(columnName)) {
                                columnName = ((Column)selectExpressionItem.getExpression()).getColumnName();
                            }
                            selectPrams.add(columnName);
                        }
                    });
                });
            }

            Assert.assertEquals(selectPrams, Lists.newArrayList("orderId","phone","address","email"));
            System.out.println("SelectItem-->" + ps.getSelectItems().get(0).toString());
        } catch (JSQLParserException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void testSubQueryJoin() {

        String sql = "SELECT a.orderId,a.phone,b.address,b.email FROM myTable a, otherTable b where a.userId = b.orderId and a.userId = 12345";

        Select select = null;
        try {
            select = (Select) CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }


        System.out.println(select.toString());


        System.out.println("Type 1: Visitor processing");
        select.getSelectBody().accept(new SelectVisitorAdapter(){
            @Override
            public void visit(PlainSelect plainSelect) {
                plainSelect.getFromItem().accept(fromVisitor);
                if (plainSelect.getJoins()!=null)
                    plainSelect.getJoins().forEach(join -> join.getRightItem().accept(fromVisitor));
            }
        });

        System.out.println("Type 2: simple method calls");
        processFromItem(((PlainSelect)select.getSelectBody()).getFromItem());
        if (((PlainSelect)select.getSelectBody()).getJoins()!=null)
            ((PlainSelect)select.getSelectBody()).getJoins().forEach(join -> processFromItem(join.getRightItem()));


        System.out.println("Type 3: hierarchically process all subselects");
        select.getSelectBody().accept(new SelectDeParser() {
            @Override
            public void visit(SubSelect subSelect) {
                System.out.println("  found subselect=" + subSelect.toString());
                super.visit(subSelect);             }
        });


    }

    private final static FromItemVisitorAdapter fromVisitor = new FromItemVisitorAdapter() {
        @Override
        public void visit(SubSelect subSelect) {
            System.out.println("subselect=" + subSelect);
        }

        @Override
        public void visit(Table table) {
            System.out.println("table=" + table);
        }
    } ;

    private static void processFromItem(FromItem fromItem) {
        System.out.println("fromItem=" + fromItem);
    }



}

