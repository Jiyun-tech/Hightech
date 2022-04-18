// practice using two dimentional array.
// input scores of each student/subject and print total & average scores.

#include <stdio.h>

int main() {
	
	int subjectCount;
	int studentCount;
	
	// 입력 
	printf("과목 수 입력 : ");
	scanf("%d", &subjectCount);
	printf("학생 수 입력 : ");
	scanf("%d", &studentCount); 
	
	int score[studentCount][subjectCount] = {0};
	int studentSum[studentCount] = {0};
	float studentAvg[studentCount] = {0};
	int subjectSum[subjectCount] = {0};
	float subjectAvg[subjectCount] = {0};
	
	for (int indexStd = 0; indexStd < studentCount; indexStd++) {
		printf("Koposw%2d 학생의 점수 %2d개를 차례대로 입력하세요. : \n"
				, indexStd+1, subjectCount);
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
		studentAvg[indexStd] = studentSum[indexStd] / (float)subjectCount;
	}
	
	for (int indexSub = 0; indexSub < subjectCount; indexSub++) {
		subjectAvg[indexSub] = subjectSum[indexSub] / (float)studentCount;
	}
	
	// 인쇄 
	for (int indexStd = 0; indexStd < studentCount; indexStd++) {
		printf("Koposw%2d 학생의 과목 총점은 %d점, 평균은 %0.2f점입니다.\n"
				, indexStd+1, studentSum[indexStd], studentAvg[indexStd]);
	}
	
	for (int indexSub = 0; indexSub < subjectCount; indexSub++) {
		printf("과목%2d 전체 평균 : %0.2f점\n", indexSub+1, subjectAvg[indexSub]);
	}
	
	return 0;
}
