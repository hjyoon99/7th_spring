SELECT
U.UserName,          -- 사용자 이름 (닉네임)
U.UserEmail,         -- 이메일
U.UserPhone,         -- 휴대폰 번호
U.UserPoint,         -- 사용자 포인트
(SELECT COUNT(*)
FROM Review AS R
WHERE R.UserID = U.UserID) AS ReviewCount -- 작성한 리뷰 수
FROM
User AS U
WHERE
U.UserID = 1;  -- 특정 사용자의 정보 (UserID = 1인 경우)