import java.text.DecimalFormat;		// ���� ��� ���Ŀ� �޸��� �߰��ϱ� ���� Ŭ����
import java.text.SimpleDateFormat;	// ���� ��¥,�ð� ��� ��� ���� ���� Ŭ����
import java.util.Calendar;			// ���� ��¥,�ð� �޾ƿ��� ���� Ŭ����
//KOPO18 ������ / 2022.04.26
//5��_������ ����_#3-1. ������ (1) �Ѿ��ġ� (�� ���߱� ����)
public class k18_receipt01 {

	public static void main(String[] args) {
		
		int k18_iPrice = 33000;			// ���� ���� k18_iPrice (�Һ��� ����) ���� �� 33,000������ �ʱ�ȭ
		int k18_TaxFinal;				// ���� ���� k18_TaxFinal ���� (����)
		int k18_netPrice;				// ���� ���� k18_netPrice ���� (���� ����)
		float k18_TaxRate = (float)0.1;	// �Ǽ� ���� k18_TaxRate ���� �� 0.1 (10%)�� �ʱ�ȭ.
		
		// #1. ���� ���� ��� ( ���� : �ΰ��� 10%)
		k18_netPrice = (int)(k18_iPrice/(1+k18_TaxRate)); 
		// ���� ���� = ���İ���/(1+�ΰ�����) => �Ҽ��� �Ʒ� �����Ͽ� ���������� ��ȯ;
		
		// #2. �ΰ��� ���
		k18_TaxFinal = k18_iPrice - k18_netPrice;		
		// �ΰ��� = �Һ��ڰ��� - ���� ����;
		// ���� ���� ��� �� �Ҽ��� �Ʒ� ����(����)�Ͽ����Ƿ�, �ΰ����� �ݴ�� �Ҽ��� �Ʒ����� �ø� ó���� ���� ��.		
		
		// #3. ���� �� �� ������ �޸� ��� ���� DecimalFormat Ŭ���� ��üȭ -> k18_Comma
		DecimalFormat k18_comma = new DecimalFormat("###,###,###");
		
		// #4. ���� ��¥&�ð� ��� -> Calendar & SimpleDateFormat ���
		Calendar k18_cal = Calendar.getInstance();
		// Calendar�� ��ü ���� �� �ν��Ͻ�ȭ.
		SimpleDateFormat k18_sdf = new SimpleDateFormat ("YYYY/MM/dd HH:mm:ss");
		// SimpleDateFormat �� ��ü ���� �� ���� �ʱ�ȭ ("��������/����/���� �ý�:�к�:����:)
		
		// #5. ��� ���� 
		// ��� ���� ���� �� %�� s,d �� ���� ���̿� ���ڸ� �־� ��� ĭ ���� (��� : ���� ����, ���� : ���� ����)
		System.out.printf("%s\n", "�ſ����");
		System.out.printf("%-6s%-10s%25s\n", "�ܸ��� : ","2N68665898","��ǥ��ȣ : 041218");
		System.out.printf("%-6s%-10s\n", "������ : ","�Ѿ��ġ�");
		System.out.printf("%-6s%-10s\n", "�֡��� : ","��� ������ �д籸 Ȳ�����351���� 10 ,");
		System.out.printf("%s\n", "1��");
		System.out.printf("%-6s%-10s\n", "��ǥ�� : ","��â��");
		System.out.printf("%-6s%-10s%27s\n", "����� : ","752-53-00558", "TEL : 7055695");
		System.out.printf("------------------------------------------------\n"); // Ư������ : 48��
		// �ݾ� ��� ��, DecimalFormat ����Ͽ� ���ڿ� �޸� ���� => ���ڿ��� �� ��ȯ�Ǿ� %s �������� ���
		System.out.printf("%-6s%37s%-2s\n", "�ݡ���", k18_comma.format(k18_netPrice),"��"); 	// ���� �ݾ�
		System.out.printf("%-6s%37s%-2s\n", "�ΰ���", k18_comma.format(k18_TaxFinal),"��");	// �ΰ���
		System.out.printf("%-6s%37s%-2s\n", "�ա���", k18_comma.format(k18_iPrice),"��");		// ���� �ݾ�(�Һ��ڰ�)
		System.out.printf("================================================\n"); // Ư������ : 48��
		System.out.printf("%s\n", "�츮ī��");
		System.out.printf("%-6s%-22s%12s\n", "ī���ȣ : ","5387-20**-****-4613(S)","�Ͻú�");
		// ���� ��¥ �� �ð� ��� ��, SimpleDateFormat ����ϸ� Calendar �Լ��� ���� ���� ��¥&�ð� �޾ƿ�.
		System.out.printf("%-6s%-20s\n", "�ŷ��Ͻ� : ",k18_sdf.format(k18_cal.getTime()));
		System.out.printf("%-6s%-20s\n", "���ι�ȣ : ","70404427");
		System.out.printf("%-6s%-20s\n", "�ŷ���ȣ : ","357734873739");
		System.out.printf("%-5s%-12s%-5s%-12s\n", "���� : ","��ī���","���� : ","720068568");
		System.out.printf("%-5s%-20s\n", "�˸� : ","EDC����ǥ");
		System.out.printf("%-5s%-20s\n", "���� : ","TEL)1544-4700");
		System.out.printf("================================================\n"); // Ư������ : 48��
		System.out.printf("%26s\n","* �����մϴ� *");
		System.out.printf("%46s\n","ǥ��V2.08_20200212");
			
	}
}
