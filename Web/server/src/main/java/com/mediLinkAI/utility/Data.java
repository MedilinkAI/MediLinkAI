package com.mediLinkAI.utility;

public class Data {

    public static String getOtpEmailHtml(String otp, String name) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>OTP Verification</title>" +
                "    <style>" +
                "        body {" +
                "            font-family: Arial, sans-serif;" +
                "            background-color: #f4f4f4;" +
                "            margin: 0;" +
                "            padding: 0;" +
                "        }" +
                "        .container {" +
                "            max-width: 600px;" +
                "            margin: 0 auto;" +
                "            background-color: #ffffff;" +
                "            padding: 20px;" +
                "            border-radius: 10px;" +
                "            box-shadow: 0 2px 4px rgba(0,0,0,0.1);" +
                "        }" +
                "        .header {" +
                "            text-align: center;" +
                "            color: #333333;" +
                "            border-bottom: 3px solid #4CAF50;" +
                "            padding-bottom: 20px;" +
                "            margin-bottom: 30px;" +
                "        }" +
                "        .header h1 {" +
                "            margin: 0;" +
                "            font-size: 28px;" +
                "            color: #4CAF50;" +
                "        }" +
                "        .content {" +
                "            color: #555555;" +
                "            line-height: 1.6;" +
                "            font-size: 16px;" +
                "        }" +
                "        .otp-box {" +
                "            background-color: #f8f9fa;" +
                "            border: 2px dashed #4CAF50;" +
                "            border-radius: 8px;" +
                "            padding: 20px;" +
                "            text-align: center;" +
                "            margin: 30px 0;" +
                "        }" +
                "        .otp-code {" +
                "            font-size: 32px;" +
                "            font-weight: bold;" +
                "            color: #4CAF50;" +
                "            letter-spacing: 5px;" +
                "            font-family: 'Courier New', monospace;" +
                "        }" +
                "        .warning {" +
                "            background-color: #fff3cd;" +
                "            border-left: 4px solid #ffc107;" +
                "            padding: 15px;" +
                "            margin: 20px 0;" +
                "            border-radius: 4px;" +
                "        }" +
                "        .warning p {" +
                "            margin: 0;" +
                "            color: #856404;" +
                "            font-size: 14px;" +
                "        }" +
                "        .footer {" +
                "            text-align: center;" +
                "            color: #999999;" +
                "            font-size: 12px;" +
                "            margin-top: 30px;" +
                "            padding-top: 20px;" +
                "            border-top: 1px solid #eeeeee;" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class=\"container\">" +
                "        <div class=\"header\">" +
                "            <h1>NutriTrack</h1>" +
                "            <p>OTP Verification</p>" +
                "        </div>" +
                "        <div class=\"content\">" +
                "            <p>Hello," + name + "</p>" +
                "            <p>You have requested an OTP (One-Time Password) for your account verification. Please use the following code to complete your verification:</p>"
                +
                "            <div class=\"otp-box\">" +
                "                <p style=\"margin: 0 0 10px 0; color: #666666; font-size: 14px;\">Your OTP Code:</p>" +
                "                <div class=\"otp-code\">" + otp + "</div>" +
                "            </div>" +
                "            <p>This OTP will expire in <strong>10 minutes</strong>. Please do not share this code with anyone.</p>"
                +
                "            <div class=\"warning\">" +
                "                <p><strong>⚠️ Security Notice:</strong> If you did not request this OTP, please ignore this email or contact our support team immediately.</p>"
                +
                "            </div>" +
                "            <p>Thank you for using the NutriTrack!</p>" +
                "            <p>Best regards,<br>NutriTrack Team</p>" +
                "        </div>" +
                "        <div class=\"footer\">" +
                "            <p>This is an automated email. Please do not reply to this message.</p>" +
                "            <p>&copy; 2025 NutriTrack. All rights reserved.</p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }
}
