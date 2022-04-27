import java.text.DecimalFormat;		// ���� ��� ���Ŀ� �޸��� �߰��ϱ� ���� Ŭ����
import java.text.SimpleDateFormat;	// ���� ��¥,�ð� ��� ��� ���� ���� Ŭ����
import java.util.ArrayList;
import java.util.Calendar;			// ���� ��¥,�ð� �޾ƿ��� ���� Ŭ����
//KOPO18 ������ / 2022.04.26
//5��_������ ����_#3-3. ������ (3) �̸�Ʈ (�鼼,���� ����)
public class k18_receipt03 {

	public static void main(String[] args) {
		// #1. ���� ��ǰ ���� (��ǰ��, ����, �Һ��� ����, �鼼����)
		// ��ǰ��
		String[] k18_itemname = {"�޹�","�ƿ�","��ȣ��","���","����","�Ѷ��","ŷ����������","����������10��","���������2L","���κ�(200g)",
								"�赿ȫ����� 4��","��� �ع��ȼ����� 5����","�Ե� �㽬�ʰ�ũ���̾Ƹ��","�����(��)120g*20��","������ ��¡�� ���� 900g",
								"���̼� ���尩 6��","�齺�Ǳ⺻���� DARK NAVY","����ǳ�����ǹ�����������","��귣�� ǥ������� 10��","���� �����۸��",
								"ü���ηδߺ�����800g","������(��)","�̸���20kg","������","�ĵ� �׸���ź� �÷���",
								"�ĵ� ����� ġ���","�ĵ� ����� ��ٸ�","����ġ����������(5����)","�йи� ��ǣ�� �Ұ�� �������","��Ƽ������"};
		String[] k18_itemnameTaxFree = new String[30]; 	// �鼼 ��ǰ ��ǰ�� �տ� *ǥ�� �߰��� �迭 ����.
		String[] k18_itemnamePrint = new String[30]; 	// ��ǰ�� ��¿����� ������ �迭 ����. (Byte �Ѱ� ����)
		// ��ǰ ���� (���� �ܰ�)
		int[] k18_itemprice = {2000,1000,990,1200,1500,25000,15000,8000,3000,4000,
								2960,2980,4400,4580,6580,8980,29900,5900,1980,4800,
								5580,3280,51990,4770,4480,3900,3900,3950,20800,3980};
		// ��ǰ�� ���� ����
		int[] k18_itemnumber = {1,1,1,5,2,1,2,1,1,1,
								1,1,1,1,1,1,1,1,2,1,
								1,1,1,1,2,2,2,1,1,1};
		// ��ǰ�� �鼼/���� ���� (true = �鼼, false = ����)
		boolean[] k18_taxfree = {true,true,true,true,true,true,true,true,true,true,
								false,false,false,false,false,false,false,false,false,false,
								true,true,true,true,false,false,false,false,true,false};
		
		
		// #3. ��ǰ �� �Һ��� ����, �ΰ���, ���� �� ���� ���
		int k18_totalPrice = 0;					// ���� ���� (�� �Һ��� ����) ���� �� 0���� �ʱ�ȭ.
		int k18_totalNumber = 0;				// ���� ���� (�� ��ǰ ����) ���� �� 0���� �ʱ�ȭ.
		int k18_taxfreeItemTotalPrice = 0;		// ���� ���� (�鼼 ��ǰ ���� �Ѿ�) ���� �� 0���� �ʱ�ȭ.
		int k18_taxItemTotalPrice = 0;			// ���� ���� (���� ��ǰ �Һ��ڰ��� �Ѿ�) ���� �� 0���� �ʱ�ȭ.
		int k18_taxItemTotalNetPrice = 0;		// ���� ���� (���� ��ǰ ���� ���� �Ѿ�) ���� �� 0���� �ʱ�ȭ.
		int k18_tax = 0;						// ���� ���� (�� �ΰ���) ���� �� 0���� �ʱ�ȭ.
		float k18_TaxRate = (float)0.1;			// �Ǽ� ���� (���� ����) ���� �� 0.1 (10%)�� �ʱ�ȭ.
		
		// #3-1. ��ǰ �� �Һ��� ����  & �� ���� ���
		for (int k18_i = 0; k18_i < k18_itemprice.length; k18_i++) {	// ��ǰ ������ŭ for�� �ݺ�
			k18_totalPrice += (k18_itemprice[k18_i]*k18_itemnumber[k18_i]); // �� ���� = �� ���� + (��ǰ�� ���� ���� * ����)
		}
		for (int k18_i = 0; k18_i < k18_itemnumber.length; k18_i++) {	// ��ǰ ������ŭ for�� �ݺ�
			k18_totalNumber += (k18_itemnumber[k18_i]);					// �� ���� = �� ���� + ��ǰ�� ����
		}
		
		// #3-2. �鼼 ��ǰ �Ѿ� & ���� ��ǰ (����/����) �Ѿ� ���
		// (1) �鼼 ��ǰ �Ѿ� (���� 0%�̹Ƿ�, ���� ���� = ���� ����)
		for (int k18_i = 0; k18_i < k18_itemprice.length; k18_i++) {	// ��ǰ ������ŭ for�� �ݺ�
			if (k18_taxfree[k18_i] == true) { 							// �鼼 ��ǰ�� ���,
				k18_taxfreeItemTotalPrice += k18_itemprice[k18_i] * k18_itemnumber[k18_i];	// �鼼 ��ǰ �� net price�� ���� ���ϱ�
			} 
		}
		// (2) ���� ��ǰ �Ѿ� (���� ���� ����) = �Һ��ڰ��� �Ѿ� - �鼼��ǰ ���� �Ѿ�;
		k18_taxItemTotalPrice = k18_totalPrice - k18_taxfreeItemTotalPrice;
		// (3) ���� ��ǰ �� �����ݾ� (���� ����) = �鼼��ǰ �Ѿ�/(1+�ΰ�����) => �Ҽ��� �Ʒ� ���� (����)
		k18_taxItemTotalNetPrice = (int)(k18_taxItemTotalPrice/(1+k18_TaxRate)/1*1);

		// #3-3. �ΰ��� ���� ��� = �Һ��ڰ��� �Ѿ� - �鼼��ǰ ���� �Ѿ� - ������ǰ �������� �Ѿ�;
		// *����) ������ǰ ���� ���� ��� �� �Ҽ��� �Ʒ� ����(����)�Ͽ����Ƿ�, �ΰ����� �ݴ�� �Ҽ��� �Ʒ����� �ø� ó���� ���� ��.		
		k18_tax = k18_totalPrice - k18_taxfreeItemTotalPrice - k18_taxItemTotalNetPrice;		
		
		// #3-4. �鼼 ��ǰ *ǥ���ϱ� (�迭�� �Է�)
		for (int k18_i = 0; k18_i < k18_taxfree.length; k18_i++) { // ��ǰ ������ŭ for�� �ݺ�
			if (k18_taxfree[k18_i] == true) { 			// �鼼 ��ǰ�� ���,
				k18_itemnameTaxFree[k18_i] = "*"; 		// �迭�� * �߰�.
			} else if (k18_taxfree[k18_i] == false) {	// ���� ��ǰ�� ���,
				k18_itemnameTaxFree[k18_i] = "";		// �迭�� ����("") �߰�.
			}
		}
		
		// #4. ��� ��ǰ�� ���ڼ� �����ϱ�
		for (int k18_i = 0; k18_i < k18_itemname.length; k18_i++) { 	// ��ǰ ������ŭ for�� �ݺ�
			byte[] k18_byteString  = k18_itemname[k18_i].getBytes(); 	// ��ǰ���� 16������ ��ȯ�Ͽ� byte�迭�� ����. (�� ���� BYTE Ȯ�� ����)
			int k18_printLength = 15*2;									// ����� ��ǰ�� ����(byte) ���� (�ѱ� ���� 15���� ��� ����)
			
			if (k18_byteString.length == k18_printLength) {				// ����(1) : ��ǰ�� ���� == ��� ���� ����
				k18_itemnamePrint[k18_i] = k18_itemname[k18_i];			// ���� : ��ǰ�� �״�� ���
			} 
			else if (k18_byteString.length < k18_printLength ) {		// ����(2) : ��ǰ�� ���� < ��� ���� ����
				int k18_diff = k18_printLength - k18_byteString.length; // ���� : ����� ��ǰ�� byte���̸�ŭ ���� �߰� (���� = 1byte)
				String k18_blank = "";
				for (int k18_j = 0; k18_j < k18_diff; k18_j++) {
					k18_blank = k18_blank + " ";
				}
				k18_itemnamePrint[k18_i] = k18_itemname[k18_i]+k18_blank;
			} 
			else {														// ����(3) : ��ǰ�� ���� > ��� ���� ����
				int k18_minusByteCount = 0;								// ���� : ��ǰ���� �߶���ϴ� ����(byte) ���
				for (int k18_j=0; k18_j<k18_printLength; k18_j++) {
					if ( k18_byteString[k18_j] < 0) {
						k18_minusByteCount += 1;
					} else {
						k18_minusByteCount += 0;
					}
				}
				if (k18_minusByteCount%2 != 0) {
					k18_printLength--; 				// ��� ���� byte-1;
					k18_itemnamePrint[k18_i] = new String(k18_byteString, 0, k18_printLength) + " "; 
					// ���� ����(Byte)�� ���߾� ��ǰ�� �ڸ� ��, ����(1byte) ���ϱ�
				} else {
					k18_itemnamePrint[k18_i] = new String(k18_byteString, 0, k18_printLength); 
					// ���� ����(Byte)�� ���߾� ��ǰ�� �ڸ���
				}
			}
		}

		// #5. ���� �� �� ������ �޸� ��� ���� DecimalFormat Ŭ���� ��üȭ �� ���� ����
		DecimalFormat k18_comma = new DecimalFormat("###,###,###");
		
		// #6. ���� ��¥&�ð� ��� -> Calendar & SimpleDateFormat ���
		// (1) Calendar�� ��ü ���� �� �ν��Ͻ�ȭ.
		Calendar k18_cal = Calendar.getInstance();
		// (2) SimpleDateFormat �� ��ü ���� �� ���� �ʱ�ȭ ("��������.����.���� �ý�:�к�")
		SimpleDateFormat k18_sdf1 = new SimpleDateFormat ("YYYY-MM-dd HH:mm");
		// (3) SimpleDateFormat �� ��ü ���� �� ���� �ʱ�ȭ ("��������-����-���� �ý�:�к�:����")
		SimpleDateFormat k18_sdf2 = new SimpleDateFormat ("YYYY.MM.dd HH:mm:ss");
		// (4) SimpleDateFormat �� ��ü ���� �� ���� �ʱ�ȭ ("����������������")
		SimpleDateFormat k18_sdf3 = new SimpleDateFormat ("YYYYMMdd");

		System.out.printf("%-30s%-22s\n","emart","�̸�Ʈ ������ (031)888-1234");
		System.out.printf("%-30s%-22s\n","","206-86-50913 ����");
		System.out.printf("%-30s%-22s\n","","���� ������ ������� 552");
		System.out.printf("%s\n", "������ �������� ��ȯ/ȯ�� �Ұ�");
		System.out.printf("%s\n", "�����ǰ�� ����, 30�� �̳�(�ż� 7��)");
		System.out.printf("%s\n", "���Ϻ� �귣�� ���� ����(���� ����������)");
		System.out.printf("%s\n", "��ȯ/ȯ�� ���������� ����");
		System.out.printf("\n");
		System.out.printf("%-5s%-18s%31s\n", "[������]",k18_sdf1.format(k18_cal.getTime()),"POS:0011-9861");
		System.out.printf("---------------------------------------------------------\n"); // ��ȣ : 57��
		System.out.printf("%2s%-12s%10s%4s%7s\n"," ","�� ǰ ��","�ܡ���","����","�ݡ���");
		System.out.printf("---------------------------------------------------------\n"); // ��ȣ : 57��
		// �ݾ� ��� ��, DecimalFormat ����Ͽ� ���ڿ� �޸� ���� => ���ڿ��� �� ��ȯ�Ǿ� %s �������� ���
		for (int k18_i = 0; k18_i < k18_itemprice.length; k18_i++) {
			System.out.printf("%-2s%-12s%11s%3s%11s\n", 
								k18_itemnameTaxFree[k18_i], 				// �鼼,���� ���� (��� ��ǰ *���)
								k18_itemnamePrint[k18_i],					// ��ǰ��
								k18_comma.format(k18_itemprice[k18_i]),		// �ܰ�
								k18_comma.format(k18_itemnumber[k18_i]),	// ����
								k18_comma.format(k18_itemprice[k18_i]*k18_itemnumber[k18_i])); // ���� (�ܰ� * ����)
			if (k18_i%5 == 4) {		// ��ǰ ����Ʈ �ټ� �ٿ� �� ���� �� ���
				System.out.printf("---------------------------------------------------------\n"); // ��ȣ : 57��
			}
		}
		System.out.printf("%23s%27s\n", "�ѡ�ǰ�񡡼��� ", k18_comma.format(k18_totalNumber));
		System.out.printf("%23s%27s\n", "(*)�顡��������ǰ ", k18_comma.format(k18_taxfreeItemTotalPrice));
		System.out.printf("%23s%27s\n", "������������ǰ ", k18_comma.format(k18_taxItemTotalNetPrice));
		System.out.printf("%23s%27s\n", "�Ρ����������� ", k18_comma.format(k18_tax));
		System.out.printf("%23s%27s\n", "�ա����������� ", k18_comma.format(k18_totalPrice));
		System.out.printf("%-20s%31s\n", "�� �� �� �� �� ��", k18_comma.format(k18_totalPrice));
		System.out.printf("---------------------------------------------------------\n"); // ��ȣ : 57��
		System.out.printf("%-12s%34s%s%s\n", "0012 KEB �ϳ�","541707**0484","/","35860658");
		// ī�� ���� �ݾ� : �� �Һ��ڰ��� ###,### ���·� ���
		System.out.printf("%-12s%28s%s%s\n", "ī�����(IC)","�Ͻú�"," / ",k18_comma.format(k18_totalPrice)); 
		System.out.printf("---------------------------------------------------------\n"); // ��ȣ : 57��
		System.out.printf("%30s\n", "[�ż�������Ʈ ����]");
		System.out.printf("%-4s%s\n", "ȫ*��","������ ����Ʈ ��Ȳ�Դϴ�.");
		System.out.printf("%-7s%33s%10s\n", "��ȸ�߻�����Ʈ","9350**9995","164");
		System.out.printf("%-9s%30s%1s%9s%1s\n", "����(����)����Ʈ",k18_comma.format(5637),
													"(",k18_comma.format(5473),")");
		System.out.printf("%31s\n", "*�ż�������Ʈ ��ȿ�Ⱓ�� 2���Դϴ�.");
		System.out.printf("---------------------------------------------------------\n"); // ��ȣ : 57��
		// ���� ��¥ �� �ð� ��� ��, SimpleDateFormat ����ϸ� Calendar �Լ��� ���� ���� ��¥&�ð� �޾ƿ�.
		System.out.printf("%29s\n", "���űݾױ��� ���������ð� �ڵ��ο�");
		System.out.printf("%-8s%44s\n", "������ȣ : ","34��****");
		System.out.printf("%-8s%45s\n", "�����ð� : ",k18_sdf2.format(k18_cal.getTime()));
		System.out.printf("---------------------------------------------------------\n"); // ��ȣ : 57��
		System.out.printf("%-3s%-6s%s%-6s%38s\n", "ĳ��:","084599"," ","��OO","1150");
		System.out.printf("%29s\n", "������ ���ڵ� ����");
		System.out.printf("%22s%s\n", k18_sdf3.format(k18_cal.getTime()),"/00119861/00164980/31");	
	}
}
