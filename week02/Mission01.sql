SELECT
MP.PerformanceID,
M.MissionName,
M.RewardPoints,
MP.PerformDate,
MP.ReviewStatus,
M.Status
FROM
MissionPerformance AS MP
JOIN
Mission AS M ON MP.MissionID = M.MissionID
JOIN
User AS U ON MP.UserID = U.UserID
WHERE
U.UserID = 1 -- 특정 사용자의 미션을 조회 (여기서는 UserID가 1인 사용자)
AND M.Status IN ('진행중', '완료') -- 진행 중이거나 완료된 미션만 조회
ORDER BY
MP.PerformDate DESC -- 미션 수행 날짜 기준으로 정렬 (최신 순서)
LIMIT 10 OFFSET 0; -- 페이징 처리 (10개씩 표시, 첫 페이지)