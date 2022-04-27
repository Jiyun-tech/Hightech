import java.text.DecimalFormat;		// ���� ��� ���Ŀ� �޸��� �߰��ϱ� ���� Ŭ����
import java.text.SimpleDateFormat;	// ���� ��¥,�ð� ��� ��� ���� ���� Ŭ����
import java.util.ArrayList;
import java.util.Calendar;			// ���� ��¥,�ð� �޾ƿ��� ���� Ŭ����
//KOPO18 ������ / 2022.04.26
//5��_������ ����_#3-2. ������ (2) ���̼� (�ѱ� ��ǰ�� �ڸ��� ����)
public class k18_receipt02 {

	public static void main(String[] args) {
		// #1. ���� ��ǰ ���� (��ǰ��, �ڵ�, �Һ��ڰ���, ����)
		String k18_itemname1 = "ǻ��� �����ܿ븶��ũ(�ְ����)";
		String k18_itemcode1 = "1031615";
		int k18_price1 = 3000;
		int k18_amount1 = 1;
		
		String k18_itemname2 = "�����̵�ĸ���(������)(100ȣ)";
		String k18_itemcode2 = "11008152";
		int k18_price2 = 1000;
		int k18_amount2 = 1;
		
		String k18_itemname3 = "�������� ���׸�����ũ(�˷�̴�Ÿ��)";
		String k18_itemcode3 = "1020800";
		int k18_price3 = 1000;
		int k18_amount3 = 1;
		
		// #2. ��ǰ ���� ��� ����Ʈ�� �����ϱ�
		ArrayList<String> k18_itemnameAll = new ArrayList<String>(); // ��ǰ��
		k18_itemnameAll.add(k18_itemname1);
		k18_itemnameAll.add(k18_itemname2);
		k18_itemnameAll.add(k18_itemname3);
		ArrayList<String> k18_itemcodeAll = new ArrayList<String>(); // ��ǰ �ڵ�
		k18_itemcodeAll.add(k18_itemcode1);
		k18_itemcodeAll.add(k18_itemcode2);
		k18_itemcodeAll.add(k18_itemcode3);
		ArrayList<Integer> k18_itempriceAll = new ArrayList<Integer>(); // ��ǰ �ܰ�
		k18_itempriceAll.add(k18_price1);
		k18_itempriceAll.add(k18_price2);
		k18_itempriceAll.add(k18_price3);
		ArrayList<Integer> k18_itemamountAll = new ArrayList<Integer>(); // ��ǰ ����
		k18_itemamountAll.add(k18_amount1);
		k18_itemamountAll.add(k18_amount2);
		k18_itemamountAll.add(k18_amount3);
		
		// #3. ��ǰ �� �Һ��� ����, �ΰ���, ���� �� ���� ���
		int k18_totalPrice = 0;			// ���� ���� k18_totalPrice (�� �Һ��� ����) ���� �� 0���� �ʱ�ȭ.
		int k18_tax;					// ���� ���� k18_tax ���� (�� �ΰ���)
		int k18_netPrice;				// ���� ���� k18_netPrice ���� (�� ���� ����)
		float k18_TaxRate = (float)0.1;	// �Ǽ� ���� k18_TaxRate ���� �� 0.1 (10%)�� �ʱ�ȭ.
		
		// #3-1. ��ǰ �� �Һ��� ���� ���
		for (int k18_i = 0; k18_i < k18_itempriceAll.size(); k18_i++) {
			k18_totalPrice += (k18_itempriceAll.get(k18_i)*k18_itemamountAll.get(k18_i));
		}
		
		// #3-2. ���� ���� ��� ( ���� : �ΰ��� 10%)
		k18_netPrice = (int)(k18_totalPrice/(1+k18_TaxRate)); 
		// ���� ���� = ���İ���/(1+�ΰ�����) => �Ҽ��� �Ʒ� �����Ͽ� ���������� ��ȯ;
		
		// #3-3. �ΰ��� ���
		k18_tax = k18_totalPrice - k18_netPrice;		
		// �ΰ��� = �Һ��ڰ��� - ���� ����;
		// ���� ���� ��� �� �Ҽ��� �Ʒ� ����(����)�Ͽ����Ƿ�, �ΰ����� �ݴ�� �Ҽ��� �Ʒ����� �ø� ó���� ���� ��.		
		
		// #4. ��� ��ǰ�� ���ڼ� �����ϱ�
		ArrayList<String> k18_itemnamePrinteAll = new ArrayList<String>();			// ��¿� ��ǰ�� ������ ArrayList ����.
		int k18_printLength = 22;													// ����� ��ǰ�� ����(byte) ����
		for (int k18_i = 0; k18_i < k18_itemnameAll.size(); k18_i++) { 				// ��ǰ ������ŭ for�� �ݺ�
			byte[] k18_byteString  = k18_itemnameAll.get(k18_i).getBytes(); 		// ��ǰ���� 16������ ��ȯ�Ͽ� byte�迭�� ����.
			if (k18_byteString.length == k18_printLength) {							// ����(1) : ��ǰ�� ���� == ��� ���� ����
				k18_itemnamePrinteAll.add(k18_itemnameAll.get(k18_i));				// ���� : ��ǰ�� �״�� ��¿� ArrayList�� ����
			} else if (k18_byteString.length < k18_printLength) { 					// ����(2) : ��ǰ�� ���� < ��� ���� ����
				int k18_diff = k18_printLength - k18_byteString.length;				// ���� : ����� ��ǰ�� byte���̸�ŭ ���� �߰� (���� = 1byte)
				String k18_blank = "";
				for (int k18_j = 0; k18_j < k18_diff; k18_j++) {
					k18_blank = k18_blank + " ";
				}
				k18_itemnamePrinteAll.add(k18_itemnameAll.get(k18_i)+k18_blank);
			} else {																// ����(3) : ��ǰ�� ���� > ��� ���� ����
				int k18_minusByteCount = 0;											// ���� : ��ǰ���� �߶���ϴ� ����(byte) ���
				for (int k18_j=0; k18_j < k18_printLength; k18_j++) {
					if ( k18_byteString[k18_j] < 0) {
						k18_minusByteCount += 1;
					} else {
						k18_minusByteCount += 0;
					}
				}
				if (k18_minusByteCount%2 != 0) { // k18_minusByteCount�� Ȧ���� ��� 
					k18_itemnamePrinteAll.add(new String(k18_byteString, 0, k18_printLength-1)+" ");
					// ��� ���� ���̺��� 1byte �۰� ��¿� ��ǰ�� ���� ���� (��� ���� byte - 1;)
					// ���� ����(Byte)�� ���߾� ��ǰ�� �ڸ� ��, String���� ��ȯ�ϰ� ����(1byte) ���ϱ�
					// -> ��¿� �̸� �迭(k18_itemnamePrint)�� ����
				} else {						// k18_minusByteCount�� ¦���� ��� 
					k18_itemnamePrinteAll.add(new String(k18_byteString, 0, k18_printLength));
					// ��� ���� ����(Byte)�� ���߾� ��ǰ�� �ڸ� ��, String���� ��ȯ�ϰ� ����(1byte) ���ϱ�
					// -> ��¿� �̸� �迭(k18_itemnamePrint)�� ����
				}
			}
		}
			

		
		// #5. ���� �� �� ������ �޸� ��� ���� DecimalFormat Ŭ���� ��üȭ -> k18_Comma
		DecimalFormat k18_comma = new DecimalFormat("###,###,###");
		
		// #6. ���� ��¥&�ð� ��� -> Calendar & SimpleDateFormat ���
		// Calendar�� ��ü ���� �� �ν��Ͻ�ȭ.
		Calendar k18_cal = Calendar.getInstance();
		
		SimpleDateFormat k18_sdf = new SimpleDateFormat ("YYYY.MM.dd HH:mm:ss");
		SimpleDateFormat k18_sdf2 = new SimpleDateFormat ("MM��dd��");
		// SimpleDateFormat �� ��ü ���� �� ���� �ʱ�ȭ ("MM��dd��")
		
		System.out.printf("%26s\n","\"���ΰ���, ���̼�\"");
		System.out.printf("%s\n", "(��)�Ƽ����̼�_�д缭����");
		System.out.printf("%-3s%-44s\n", "��ȭ:","031-702-6016");
		System.out.printf("%-3s%-44s\n", "����:","���� ������ ���μ�ȯ�� 2748 (���)");
		System.out.printf("%-3s%-44s\n", "��ǥ:","������,��ȣ�� 213-81-52063");
		System.out.printf("%-3s%-44s\n", "����:","��⵵ ������ �д籸 �д��53���� 11 (����");
		System.out.printf("%s\n", "��)");
		System.out.printf("================================================\n"); // ��ȣ : 48��
		System.out.printf("%27s\n","�Һ����߽ɰ濵(CCM) �������");
		System.out.printf("%29s\n","ISO 9001 ǰ���濵�ý��� �������");
		System.out.printf("================================================\n"); // ��ȣ : 48��
		System.out.printf("%19s%4s%3s\n","��ȯ/ȯ�� 14��(",k18_sdf2.format(k18_cal.getTime()),")�̳�,");
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// !!!!ȯ�� ���� ���� ���� ����غ���!!!!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.printf("%28s\n","(����)������, ����ī�� ���� �� ���Ը��忡�� ����");
		System.out.printf("%27s\n","����/���� �� �Ѽս� ��ȯ/ȯ�� �Ұ�");
		System.out.printf("%27s\n","üũī�� ��� �� �ִ� 7�� �ҿ�");
		System.out.printf("================================================\n"); // ��ȣ : 48��
		// ���� ��¥ �� �ð� ��� ��, SimpleDateFormat ����ϸ� Calendar �Լ��� ���� ���� ��¥&�ð� �޾ƿ�.
		System.out.printf("%-15s%33s\n", "[POS 1058231]", k18_sdf.format(k18_cal.getTime()));
		// �ݾ� ��� ��, DecimalFormat ����Ͽ� ���ڿ� �޸� ���� => ���ڿ��� �� ��ȯ�Ǿ� %s �������� ���
		System.out.printf("================================================\n"); // ��ȣ : 48��
		for (int k18_i = 0; k18_i < k18_itempriceAll.size(); k18_i++) {
			System.out.printf("%-13s%11s%3s%11s\n", k18_itemnamePrinteAll.get(k18_i),
								k18_comma.format(k18_itempriceAll.get(k18_i)),
								k18_comma.format(k18_itemamountAll.get(k18_i)),
								k18_comma.format(k18_itempriceAll.get(k18_i) * k18_itemamountAll.get(k18_i)));
			System.out.printf("[%s]\n",k18_itemcodeAll.get(k18_i));
		}
		System.out.printf("%15s%29s\n", "�����հ�", k18_comma.format(k18_netPrice));
		System.out.printf("%15s%29s\n", "���ΰ���", k18_comma.format(k18_tax));		
		System.out.printf("------------------------------------------------\n"); // ��ȣ : 48��
		System.out.printf("%-33s%11s\n", "�Ǹ��հ�", k18_comma.format(k18_totalPrice));
		System.out.printf("================================================\n"); // ��ȣ : 48��
		System.out.printf("%-33s%11s\n", "�ſ�ī��", k18_comma.format(k18_totalPrice));
		System.out.printf("------------------------------------------------\n"); // ��ȣ : 48��
		System.out.printf("%-15s%29s\n", "�츮ī��", "538720**********");
		System.out.printf("%-4s%1s%-12s%17s%s%s\n", "���ι�ȣ"," ","77982843(0)","���αݾ�"," ",k18_comma.format(k18_totalPrice));
		System.out.printf("================================================\n"); // ��ȣ : 48��
		// ���� ��¥ �� �ð� ��� ��, SimpleDateFormat ����ϸ� Calendar �Լ��� ���� ���� ��¥&�ð� �޾ƿ�.
		System.out.printf("%28s%1s%5s\n", k18_sdf.format(k18_cal.getTime())," ","�д缭����");
		System.out.printf("%-8s%-20s\n", "��ǰ �� ��Ÿ ���� : ","1522-4400");
		System.out.printf("%-8s%-20s\n", "����� �� �����̼� ���� ���� : ","1599-2211");
		System.out.printf("%25s\n", "������ ���ڵ� ����");
		System.out.printf("%32s\n", "2112820610158231");
		System.out.printf("------------------------------------------------\n"); // ��ȣ : 48��
		System.out.printf("%27s\n","�� ���̼� ����� �� �Ǵ� Ȩ�������� �����ϼż�");
		System.out.printf("%27s\n","ȸ������ �� �پ��� ������ ����������! ��");
			
	}
}
