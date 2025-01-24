import cv2
import numpy as np
from math import sqrt

# Function to calculate edge lengths of detected AprilTags
def calculate_edge_lengths(corner_group):
    corner_points = corner_group[0]
    edge_lengths = []
    for i in range(4):
        p1 = corner_points[i]
        p2 = corner_points[(i + 1) % 4]  # Wrap around for the last edge
        length = sqrt((p2[0] - p1[0]) ** 2 + (p2[1] - p1[1]) ** 2)
        edge_lengths.append(length)
    return edge_lengths

# Initialize the video capture from the onboard camera
cap = cv2.VideoCapture(0)  # '0' for default laptop camera

# Initialize the AprilTag detector
detector = cv2.aruco.ArucoDetector()

print("Press 'q' to quit.")

while True:
    # Capture a frame from the camera
    ret, frame = cap.read()
    if not ret:
        print("Failed to grab frame.")
        break

    # Convert the frame to grayscale for processing
    gray_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    # Detect AprilTags in the frame
    corners, ids, _ = detector.detectMarkers(gray_frame)

    # Process detected tags
    if ids is not None:
        for i, corner_group in enumerate(corners):
            # Calculate the edge lengths of the AprilTag
            edge_lengths = calculate_edge_lengths(corner_group)
            print(f"Tag ID {ids[i][0]} Edge Lengths (px): {edge_lengths}")

            # Draw the detected tag on the frame
            frame = cv2.polylines(frame, [np.int32(corner_group[0])], True, (0, 255, 0), 2)
            # Add tag ID text on the frame
            center = np.mean(corner_group[0], axis=0).astype(int)
            cv2.putText(frame, f"ID: {ids[i][0]}", tuple(center), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 255), 2)

    # Display the frame with detected AprilTags
    cv2.imshow("AprilTag Detection", frame)

    # Break the loop when 'q' is pressed
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# Release the video capture and close OpenCV windows
cap.release()
cv2.destroyAllWindows()
