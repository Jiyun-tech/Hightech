#include <stdio.h>

int main() {
	
	int subjectCount;
	int studentCount;
	int score[20][10];
	int studentSum[20];
	float studentAvg[20];
	int subjectSum[10];
	float subjectAvg[10];
	
	// 입력 
	printf("과목 수 입력 : ");
	scanf("%d", &subjectCount);
	printf("학생 수 입력 : ");
	scanf("%d", &studentCount); 
	
	for (int indexStd = 0; indexStd < studentCount; indexStd++) {
		printf("Koposw%2d 학생의 점수 %2d개를 차례대로 입력하세요. : \n", indexStd+1, subjectCount);
		for (int indexSub = 0; indexSub < subjectCount; indexSub++) {
			scanf("%d", &score[indexStd][indexSub]);
		}
	}
	
	// 계산 
	for (int indexStd = 0; indexStd < studentCount; indexStd++) {
		for (int indexSub = 0; indexSub < subjectCount; indexSub++) {
			studentSum[indexStd] += score[indexStd][indexSub];
			subjectSum[indexSub] += score[indexStd][indexSub];
		}
		studentAvg[indexStd] = studentSum[indexStd]/(float)subjectCount;
	}
	
	for (int indexSub = 0; indexSub < subjectCount; indexSub++) {
		subjectAvg[indexSub] = subjectSum[indexSub]/(float)studentCount;
	}
	
	// 인쇄 
	for (int indexStd = 0; indexStd < studentCount; indexStd++) {
		printf("Koposw%2d 학생의 과목 총점은 %d점, 평균은 %0.2f점입니다.\n", indexStd+1, studentSum[indexStd], studentAvg[indexStd]);
	}
	
	for (int indexSub = 0; indexSub < studentCount; indexSub++) {
		printf("과목%2d 전체 평균 : %0.2f점\n", indexSub+1, subjectAvg[indexSub]);
	}
	
	return 0;
}
