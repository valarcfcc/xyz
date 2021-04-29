package com.valarcfcc.xyz;

import com.valarcfcc.xyz.utils.StringUtils;
import org.junit.Test;
import static com.valarcfcc.xyz.utils.Common.println;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class InterestTest {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new Exception("请输入正确的" + tip + "！");
    }

    private static final BigDecimal INTEREST_RATE = new BigDecimal("6");
    private static final BigDecimal PENALTY_INTEREST_RATE = new BigDecimal("12");
    private static final BigDecimal YEAR = new BigDecimal("36000");
    private static final int REPAYMENT_DATE = 21;
    @Test
    public void calculate(){
        // 账户余额
        BigDecimal accNoAmt = new BigDecimal("10000");
        // 本金
        BigDecimal principal = BigDecimal.ZERO;
        // 当期本金
        BigDecimal currentPrincipal = BigDecimal.ZERO;
        // 逾期本金
        BigDecimal overduePrincipal = BigDecimal.ZERO;
        // 当前利息
        BigDecimal currentInterest = BigDecimal.ZERO;
        // 逾期利息
        BigDecimal overdueInterest = BigDecimal.ZERO;
        // 当期罚息
        BigDecimal currentPenaltyInterest = BigDecimal.ZERO;
        // 逾期罚息
        BigDecimal overduePenaltyInterest = BigDecimal.ZERO;
        // 当期复利
        BigDecimal currentCompoundInterest = BigDecimal.ZERO;
        // 逾期复利
        BigDecimal overdueCompoundInterest = BigDecimal.ZERO;
        // 当期罚息的复利
        BigDecimal currentCompoundPenaltyInterest = BigDecimal.ZERO;
        // 逾期罚息的复利
        BigDecimal overdueCompoundPenaltyInterest = BigDecimal.ZERO;
        LocalDate date = LocalDate.of(2019,5,25);

        principal = new BigDecimal("600000");

        BigDecimal count = new BigDecimal(10*12);

        currentPrincipal = principal.divide(count);

        LocalDate firstRepaymentDate;

        if (date.getDayOfMonth() > REPAYMENT_DATE){
            firstRepaymentDate = LocalDate.of(date.getYear(),date.getMonthValue(),REPAYMENT_DATE);
            firstRepaymentDate = firstRepaymentDate.plusMonths(1);
        } else {
            firstRepaymentDate = LocalDate.of(date.getYear(),date.getMonthValue(),REPAYMENT_DATE);
        }

        System.out.printf("首次还贷日：%d年%d月%d日\n", firstRepaymentDate.getYear(), firstRepaymentDate.getMonthValue(), firstRepaymentDate.getDayOfMonth());
        Period period = Period.between(date, firstRepaymentDate);

        BigDecimal days = new BigDecimal( period.getDays());

        currentInterest = principal.multiply(days).multiply(INTEREST_RATE).divide(YEAR);

        currentPenaltyInterest = overduePrincipal.multiply(days).multiply(PENALTY_INTEREST_RATE).divide(YEAR);

        currentCompoundInterest = overdueInterest.multiply(days).multiply(PENALTY_INTEREST_RATE).divide(YEAR);

        currentCompoundPenaltyInterest = overduePenaltyInterest.multiply(days).multiply(PENALTY_INTEREST_RATE).divide(YEAR);

        println("本金:" +principal);
        println("当期本金:" +currentPrincipal);
        println("逾期本金:" +overduePrincipal);
        println("当前利息:" +currentInterest);
        println("逾期利息:" +overdueInterest);
        println("当期罚息:" +currentPenaltyInterest);
        println("逾期罚息:" +overduePenaltyInterest);
        println("当期复利:" +currentCompoundInterest);
        println("逾期复利:" +overdueCompoundInterest);
        println("当期罚息的复利:" +currentCompoundPenaltyInterest);
        println("逾期罚息的复利:" +overdueCompoundPenaltyInterest);




        LocalDate oldDate = LocalDate.of(2019,5,25);




        //
        //
        //
        //
        //
        //

    }


}
