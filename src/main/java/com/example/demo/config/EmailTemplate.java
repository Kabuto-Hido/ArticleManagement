package com.example.demo.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmailTemplate {
    public static String TemplateRecoveryPassword(String username, String otp) {
        return "\n" +
                "<!doctype html>\n" +
                "<html lang=\"en-US\">\n" +
                "<head>\n" +
                "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "    <title>Reset Password Email Template</title>\n" +
                "    <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
                "    <style type=\"text/css\">\n" +
                "        a:hover {text-decoration: underline !important;}\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                "    <!--100% body table-->\n" +
                "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align:center;\">\n" +
                "                          <a href=\"\" title=\"logo\" target=\"_blank\">\n" +
                "                            <img width=\"400\" height=\"200\" src=\"https://logolook.net/wp-content/uploads/2021/11/Shopee-Logo.png\" title=\"logo\" alt=\"logo\">\n" +
                "                          </a>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                "                                <tr>\n" +
                "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"padding:0 35px;\">\n" +
                "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have\n" +
                "                                            requested to change your account password " + username + "</h1>\n" +
                "                                        <span\n" +
                "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
                "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
                "                                            You have requested to change your account password for " + username + "\n" + "<br>" +
                "                                            Please use this OTP for authentication: \n" +
                "                                        </p>\n" +
                "                                        <a \n" +
                "                                            style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">" + otp +
                "                                            </a>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align:center;\">\n" +
                "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>Thực tập FPT software - Nhóm 2 - Made with love</strong></p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <!--/100% body table-->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String TemplateCheckValidEmail(String username, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#C91616\">\n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#C91616\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm to be a part of JD</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + username + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes." +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    public static String TemplateInvoice(long orderId, String customerName, String email, String paymentMethod,
                                         String createdDate, String des, String amount) throws IOException {
        String header = new String(Files.readAllBytes(Paths.get("src\\main\\java\\com\\example\\demo\\file\\TemplateInvoice.txt")));
        return header + "\n" + "<body style=\"word-spacing: normal; background-color: rgb(235, 234, 238); opacity: 1; visibility: visible; animation: auto ease 0s 1 normal none running none;\"\n" +
                "        class=\"amp-mode-mouse\">\n" +
                "        <div\n" +
                "                style=\"color:transparent;visibility:hidden;opacity:0;font-size:0px;border:0;max-height:1px;width:1px;margin:0px;padding:0px;border-width:0px;line-height:0px\">\n" +
                "                <amp-img alt=\"\" height=\"1\"\n" +
                "                        src=\"https://ci4.googleusercontent.com/proxy/B1vXjukac-dn-dBwGhrLussanuxul8J6TwpRc8b9APH6JGj3F33cTZc9lH12X32MfeLmHkguLNWj26mamXK-DImMacWuRAL6zSbOcHUSc-MNp3tdaz6tqda65Ho-kCky0sUSdPD1RvgRpzneFcyqUPe0F3Qn3jquNrJTMbr3LkXxCR50DmN1fEBCae2i5jZRvsH635EgwXD6K2U2tw=s0-d-e1-ft#https://sp-t1.mmtrkr.com/q/KRKeh9bNEu6GknAMJdj6NA~~/AASgtwA~/RgRnJeWNPVcDc3BjQgplQY1gQ2Xg5PWbUhduZ29idWl0dWFuYW5oQGdtYWlsLmNvbSJYBAAAAnY~\"\n" +
                "                        width=\"1\"\n" +
                "                        class=\"i-amphtml-element i-amphtml-layout-fixed i-amphtml-layout-size-defined i-amphtml-built i-amphtml-layout\"\n" +
                "                        i-amphtml-layout=\"fixed\" style=\"width: 1px; height: 1px;\"><img decoding=\"async\" alt=\"\"\n" +
                "                                src=\"https://ci4.googleusercontent.com/proxy/B1vXjukac-dn-dBwGhrLussanuxul8J6TwpRc8b9APH6JGj3F33cTZc9lH12X32MfeLmHkguLNWj26mamXK-DImMacWuRAL6zSbOcHUSc-MNp3tdaz6tqda65Ho-kCky0sUSdPD1RvgRpzneFcyqUPe0F3Qn3jquNrJTMbr3LkXxCR50DmN1fEBCae2i5jZRvsH635EgwXD6K2U2tw=s0-d-e1-ft#https://sp-t1.mmtrkr.com/q/KRKeh9bNEu6GknAMJdj6NA~~/AASgtwA~/RgRnJeWNPVcDc3BjQgplQY1gQ2Xg5PWbUhduZ29idWl0dWFuYW5oQGdtYWlsLmNvbSJYBAAAAnY~\"\n" +
                "                                class=\"i-amphtml-fill-content i-amphtml-replaced-content\"></amp-img>\n" +
                "        </div>\n" +
                "        <div style=\"background-color:#ebeaee\">\n" +
                "                <div style=\"background:#fff;background-color:#fff;margin:0 auto;max-width:600px\">\n" +
                "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"\n" +
                "                                style=\"background:#fff;background-color:#fff;width:100%\">\n" +
                "                                <tbody>\n" +
                "                                        <tr>\n" +
                "                                                <td\n" +
                "                                                        style=\"direction:ltr;font-size:0;padding:0;padding-bottom:0;padding-left:0;padding-right:0;padding-top:0;text-align:center\">\n" +
                "                                                        <div\n" +
                "                                                                style=\"background:#fff;background-color:#fff;margin:0 auto;max-width:600px\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                        cellspacing=\"0\" role=\"presentation\"\n" +
                "                                                                        style=\"background:#fff;background-color:#fff;width:100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                        <td\n" +
                "                                                                                                style=\"border:0 solid #1e293b;direction:ltr;font-size:0;padding:20px 0;padding-bottom:16px;padding-left:30px;padding-right:30px;padding-top:16px;text-align:center\">\n" +
                "                                                                                                <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                                                                                                        style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\n" +
                "                                                                                                        <table border=\"0\"\n" +
                "                                                                                                                cellpadding=\"0\"\n" +
                "                                                                                                                cellspacing=\"0\"\n" +
                "                                                                                                                role=\"presentation\"\n" +
                "                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top\"\n" +
                "                                                                                                                width=\"100%\">\n" +
                "                                                                                                                <tbody>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td align=\"left\"\n" +
                "                                                                                                                                        style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                        <div\n" +
                "                                                                                                                                                style=\"font-family:Helvetica;font-size:16px;font-weight:400;letter-spacing:0;line-height:1.5;text-align:left;color:#1e293b\">\n" +
                "                                                                                                                                                <p\n" +
                "                                                                                                                                                        style=\"text-align:center\">\n" +
                "                                                                                                                                                        <strong>KNIGHT POST</strong>\n" +
                "                                                                                                                                                </p>\n" +
                "                                                                                                                                        </div>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                </tbody>\n" +
                "                                                                                                        </table>\n" +
                "                                                                                                </div>\n" +
                "                                                                                        </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                </table>\n" +
                "                                                        </div>\n" +
                "                                                        <div\n" +
                "                                                                style=\"background:#fff;background-color:#fff;margin:0 auto;max-width:600px\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                        cellspacing=\"0\" role=\"presentation\"\n" +
                "                                                                        style=\"background:#fff;background-color:#fff;width:100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                        <td\n" +
                "                                                                                                style=\"border:0 solid #fff;direction:ltr;font-size:0;padding:20px 0;padding-bottom:1px;padding-left:20px;padding-right:20px;padding-top:1px;text-align:center\">\n" +
                "                                                                                                <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                                                                                                        style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\n" +
                "                                                                                                        <table border=\"0\"\n" +
                "                                                                                                                cellpadding=\"0\"\n" +
                "                                                                                                                cellspacing=\"0\"\n" +
                "                                                                                                                role=\"presentation\"\n" +
                "                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top\"\n" +
                "                                                                                                                width=\"100%\">\n" +
                "                                                                                                                <tbody>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td\n" +
                "                                                                                                                                        style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                        <p\n" +
                "                                                                                                                                                style=\"border-top:solid 1px #ebebeb;font-size:1px;margin:0 auto;width:100%\">\n" +
                "                                                                                                                                        </p>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                </tbody>\n" +
                "                                                                                                        </table>\n" +
                "                                                                                                </div>\n" +
                "                                                                                        </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                </table>\n" +
                "                                                        </div>\n" +
                "                                                        <div\n" +
                "                                                                style=\"background:#fff;background-color:#fff;margin:0 auto;max-width:600px\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                        cellspacing=\"0\" role=\"presentation\"\n" +
                "                                                                        style=\"background:#fff;background-color:#fff;width:100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                        <td\n" +
                "                                                                                                style=\"border:0 solid transparent;direction:ltr;font-size:0;padding:20px 0;padding-bottom:20px;padding-left:30px;padding-right:30px;padding-top:0;text-align:center\">\n" +
                "                                                                                                <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                                                                                                        style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\n" +
                "                                                                                                        <table border=\"0\"\n" +
                "                                                                                                                cellpadding=\"0\"\n" +
                "                                                                                                                cellspacing=\"0\"\n" +
                "                                                                                                                role=\"presentation\"\n" +
                "                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top\"\n" +
                "                                                                                                                width=\"100%\">\n" +
                "                                                                                                                <tbody>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td align=\"left\"\n" +
                "                                                                                                                                        style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                        <div\n" +
                "                                                                                                                                                style=\"font-family:Helvetica;font-size:32px;font-weight:400;letter-spacing:0;line-height:1.5;text-align:left;color:#444\">\n" +
                "                                                                                                                                                <p\n" +
                "                                                                                                                                                        style=\"line-height:1;text-align:center\">\n" +
                "                                                                                                                                                        <span\n" +
                "                                                                                                                                                                style=\"color:#334155;font-size:30px\"><strong>Thank you for your order</strong></span>\n" +
                "                                                                                                                                                </p>\n" +
                "                                                                                                                                                <p\n" +
                "                                                                                                                                                        style=\"line-height:1;text-align:center\">\n" +
                "                                                                                                                                                        <span\n" +
                "                                                                                                                                                                style=\"color:#94a3b8;font-size:14px\">We've\n" +
                "                                                                                                                                                                received\n" +
                "                                                                                                                                                                your\n" +
                "                                                                                                                                                                payment\n" +
                "                                                                                                                                                                and\n" +
                "                                                                                                                                                                here\n" +
                "                                                                                                                                                                is\n" +
                "                                                                                                                                                                a\n" +
                "                                                                                                                                                                summary\n" +
                "                                                                                                                                                                for\n" +
                "                                                                                                                                                                the\n" +
                "                                                                                                                                                                same</span>\n" +
                "                                                                                                                                                </p>\n" +
                "                                                                                                                                        </div>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                </tbody>\n" +
                "                                                                                                        </table>\n" +
                "                                                                                                </div>\n" +
                "                                                                                        </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                </table>\n" +
                "                                                        </div>\n" +
                "                                                        <div\n" +
                "                                                                style=\"background:0 0;background-color:transparent;margin:0 auto;max-width:600px\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                        cellspacing=\"0\" role=\"presentation\"\n" +
                "                                                                        style=\"background:0 0;background-color:transparent;width:100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                        <td\n" +
                "                                                                                                style=\"border:0 solid transparent;direction:ltr;font-size:0;padding:20px 0;padding-bottom:20px;padding-left:30px;padding-right:30px;padding-top:0;text-align:center\">\n" +
                "                                                                                                <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                                                                                                        style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\n" +
                "                                                                                                        <table border=\"0\"\n" +
                "                                                                                                                cellpadding=\"0\"\n" +
                "                                                                                                                cellspacing=\"0\"\n" +
                "                                                                                                                role=\"presentation\"\n" +
                "                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top\"\n" +
                "                                                                                                                width=\"100%\">\n" +
                "                                                                                                                <tbody>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td\n" +
                "                                                                                                                                        style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                        <p\n" +
                "                                                                                                                                                style=\"border-top:solid 1px #ebebeb;font-size:1px;margin:0 auto;width:100%\">\n" +
                "                                                                                                                                        </p>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                </tbody>\n" +
                "                                                                                                        </table>\n" +
                "                                                                                                </div>\n" +
                "                                                                                        </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                </table>\n" +
                "                                                        </div>\n" +
                "                                                        <div\n" +
                "                                                                style=\"background:#fff;background-color:#fff;margin:0 auto;max-width:600px\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                        cellspacing=\"0\" role=\"presentation\"\n" +
                "                                                                        style=\"background:#fff;background-color:#fff;width:100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                        <td\n" +
                "                                                                                                style=\"border:0 solid #1e293b;direction:ltr;font-size:0;padding:20px 0;padding-bottom:0;padding-left:30px;padding-right:30px;padding-top:20px;text-align:center\">\n" +
                "                                                                                                <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                                                                                                        style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\n" +
                "                                                                                                        <table border=\"0\"\n" +
                "                                                                                                                cellpadding=\"0\"\n" +
                "                                                                                                                cellspacing=\"0\"\n" +
                "                                                                                                                role=\"presentation\"\n" +
                "                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top\"\n" +
                "                                                                                                                width=\"100%\">\n" +
                "                                                                                                                <tbody>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td align=\"left\"\n" +
                "                                                                                                                                        style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                        <div\n" +
                "                                                                                                                                                style=\"font-family:Helvetica;font-size:16px;font-weight:400;letter-spacing:0;line-height:1.5;text-align:left;color:#1e293b\">\n" +
                "                                                                                                                                                <p\n" +
                "                                                                                                                                                        style=\"line-height:2\">\n" +
                "                                                                                                                                                        <strong>Invoice #" + orderId + " </strong>\n" +
                "                                                                                                                                                </p>\n" +
                "                                                                                                                                        </div>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                </tbody>\n" +
                "                                                                                                        </table>\n" +
                "                                                                                                </div>\n" +
                "                                                                                        </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                </table>\n" +
                "                                                        </div>\n" +
                "                                                        <div\n" +
                "                                                                style=\"background:#fff;background-color:#fff;margin:0 auto;max-width:600px\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                        cellspacing=\"0\" role=\"presentation\"\n" +
                "                                                                        style=\"background:#fff;background-color:#fff;width:100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                        <td\n" +
                "                                                                                                style=\"border:0 solid #1e293b;direction:ltr;font-size:0;padding:20px 0;padding-bottom:20px;padding-left:30px;padding-right:30px;padding-top:20px;text-align:center\">\n" +
                "                                                                                                <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                                                                                                        style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\n" +
                "                                                                                                        <table border=\"0\"\n" +
                "                                                                                                                cellpadding=\"0\"\n" +
                "                                                                                                                cellspacing=\"0\"\n" +
                "                                                                                                                role=\"presentation\"\n" +
                "                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top\"\n" +
                "                                                                                                                width=\"100%\">\n" +
                "                                                                                                                <tbody>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td align=\"left\"\n" +
                "                                                                                                                                        style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                        <div\n" +
                "                                                                                                                                                style=\"font-family:Helvetica;font-size:16px;font-weight:400;letter-spacing:0;line-height:1.5;text-align:left;color:#1e293b\">\n" +
                "                                                                                                                                                <p><span style=\"color:#64748b\">Customer:</span></p>\n" +
                "                                                                                                                                                <p><span style=\"color:#1e293b\"><strong>" + customerName + "</strong>\n" +
                "                                                                                                                                                                (</span><span\n" +
                "                                                                                                                                                                style=\"color:#1e293b;font-size:14px\">" + email + ")</span>\n" +
                "                                                                                                                                                </p>\n" +
                "                                                                                                                                        </div>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td\n" +
                "                                                                                                                                        style=\"background:0 0;font-size:0;word-break:break-word\">\n" +
                "                                                                                                                                        <div\n" +
                "                                                                                                                                                style=\"height:20px\">\n" +
                "                                                                                                                                                &nbsp;\n" +
                "                                                                                                                                        </div>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td align=\"left\"\n" +
                "                                                                                                                                        style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                        <div\n" +
                "                                                                                                                                                style=\"font-family:Helvetica;font-size:16px;font-weight:400;letter-spacing:0;line-height:1.5;text-align:left;color:#1e293b\">\n" +
                "                                                                                                                                                <p><span style=\"color:#64748b\">Payment method:</span></p>\n" +
                "                                                                                                                                                <p><span style=\"color:#1e293b\"><strong>Credit Card - " + paymentMethod + "</strong></span>\n" +
                "                                                                                                                                                </p>\n" +
                "                                                                                                                                        </div>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td\n" +
                "                                                                                                                                        style=\"background:0 0;font-size:0;word-break:break-word\">\n" +
                "                                                                                                                                        <div\n" +
                "                                                                                                                                                style=\"height:20px\">\n" +
                "                                                                                                                                                &nbsp;\n" +
                "                                                                                                                                        </div>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td align=\"left\"\n" +
                "                                                                                                                                        style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                        <div\n" +
                "                                                                                                                                                style=\"font-family:Helvetica;font-size:16px;font-weight:400;letter-spacing:0;line-height:1.5;text-align:left;color:#1e293b\">\n" +
                "                                                                                                                                                <p><span style=\"color:#64748b\">Date of payment:</span></p>\n" +
                "                                                                                                                                                <p><span style=\"color:#1e293b\"><strong>" + createdDate + "</strong></span>\n" +
                "                                                                                                                                                </p>\n" +
                "                                                                                                                                        </div>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                </tbody>\n" +
                "                                                                                                        </table>\n" +
                "                                                                                                </div>\n" +
                "                                                                                        </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                </table>\n" +
                "                                                        </div>\n" +
                "                                                        <div\n" +
                "                                                                style=\"background:0 0;background-color:transparent;margin:0 auto;max-width:600px\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                        cellspacing=\"0\" role=\"presentation\"\n" +
                "                                                                        style=\"background:0 0;background-color:transparent;width:100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                        <td\n" +
                "                                                                                                style=\"border:0 solid transparent;direction:ltr;font-size:0;padding:20px 0;padding-bottom:0;padding-left:20px;padding-right:20px;padding-top:0;text-align:center\">\n" +
                "                                                                                                <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                                                                                                        style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\n" +
                "                                                                                                        <table border=\"0\"\n" +
                "                                                                                                                cellpadding=\"0\"\n" +
                "                                                                                                                cellspacing=\"0\"\n" +
                "                                                                                                                role=\"presentation\"\n" +
                "                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top\"\n" +
                "                                                                                                                width=\"100%\">\n" +
                "                                                                                                                <tbody>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td\n" +
                "                                                                                                                                        style=\"font-size:0;padding:10px 25px;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;word-break:break-word\">\n" +
                "                                                                                                                                        <p\n" +
                "                                                                                                                                                style=\"border-top:solid 1px #ebebeb;font-size:1px;margin:0 auto;width:100%\">\n" +
                "                                                                                                                                        </p>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                </tbody>\n" +
                "                                                                                                        </table>\n" +
                "                                                                                                </div>\n" +
                "                                                                                        </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                </table>\n" +
                "                                                        </div>\n" +
                "                                                        <div\n" +
                "                                                                style=\"background:0 0;background-color:transparent;margin:0 auto;max-width:600px\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                        cellspacing=\"0\" role=\"presentation\"\n" +
                "                                                                        style=\"background:0 0;background-color:transparent;width:100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                        <td\n" +
                "                                                                                                style=\"border:0 solid transparent;direction:ltr;font-size:0;padding:20px 0;padding-bottom:10px;padding-left:30px;padding-right:30px;padding-top:20px;text-align:center\">\n" +
                "                                                                                                <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                                                                                                        style=\"font-size:0;line-height:0;text-align:left;display:inline-block;width:100%;direction:ltr\">\n" +
                "                                                                                                        <div class=\"mj-column-per-47 mj-outlook-group-fix\"\n" +
                "                                                                                                                style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:47%\">\n" +
                "                                                                                                                <table border=\"0\"\n" +
                "                                                                                                                        cellpadding=\"0\"\n" +
                "                                                                                                                        cellspacing=\"0\"\n" +
                "                                                                                                                        role=\"presentation\"\n" +
                "                                                                                                                        width=\"100%\">\n" +
                "                                                                                                                        <tbody>\n" +
                "                                                                                                                                <tr>\n" +
                "                                                                                                                                        <td\n" +
                "                                                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0\">\n" +
                "                                                                                                                                                <table border=\"0\"\n" +
                "                                                                                                                                                        cellpadding=\"0\"\n" +
                "                                                                                                                                                        cellspacing=\"0\"\n" +
                "                                                                                                                                                        role=\"presentation\"\n" +
                "                                                                                                                                                        width=\"100%\">\n" +
                "                                                                                                                                                        <tbody>\n" +
                "                                                                                                                                                                <tr>\n" +
                "                                                                                                                                                                        <td align=\"left\"\n" +
                "                                                                                                                                                                                style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                                                                <div\n" +
                "                                                                                                                                                                                        style=\"font-family:Helvetica;font-size:16px;font-weight:400;letter-spacing:0;line-height:1.5;text-align:left;color:#444\">\n" +
                "                                                                                                                                                                                        <p><span style=\"color:#64748b;font-size:16px\"><strong>Description&nbsp;</strong></span>\n" +
                "                                                                                                                                                                                        </p>\n" +
                "                                                                                                                                                                                </div>\n" +
                "                                                                                                                                                                        </td>\n" +
                "                                                                                                                                                                </tr>\n" +
                "                                                                                                                                                        </tbody>\n" +
                "                                                                                                                                                </table>\n" +
                "                                                                                                                                        </td>\n" +
                "                                                                                                                                </tr>\n" +
                "                                                                                                                        </tbody>\n" +
                "                                                                                                                </table>\n" +
                "                                                                                                        </div>\n" +
                "                                                                                                        <div class=\"mj-column-per-53 mj-outlook-group-fix\"\n" +
                "                                                                                                                style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:53%\">\n" +
                "                                                                                                                <table border=\"0\"\n" +
                "                                                                                                                        cellpadding=\"0\"\n" +
                "                                                                                                                        cellspacing=\"0\"\n" +
                "                                                                                                                        role=\"presentation\"\n" +
                "                                                                                                                        width=\"100%\">\n" +
                "                                                                                                                        <tbody>\n" +
                "                                                                                                                                <tr>\n" +
                "                                                                                                                                        <td\n" +
                "                                                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0\">\n" +
                "                                                                                                                                                <table border=\"0\"\n" +
                "                                                                                                                                                        cellpadding=\"0\"\n" +
                "                                                                                                                                                        cellspacing=\"0\"\n" +
                "                                                                                                                                                        role=\"presentation\"\n" +
                "                                                                                                                                                        width=\"100%\">\n" +
                "                                                                                                                                                        <tbody>\n" +
                "                                                                                                                                                                <tr>\n" +
                "                                                                                                                                                                        <td align=\"left\"\n" +
                "                                                                                                                                                                                style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                                                                <div\n" +
                "                                                                                                                                                                                        style=\"font-family:Helvetica;font-size:16px;font-weight:400;letter-spacing:0;line-height:1.5;text-align:left;color:#444\">\n" +
                "                                                                                                                                                                                        <p><span style=\"color:#64748b\">"+ des +"</span>\n" +
                "                                                                                                                                                                                        </p>\n" +
                "                                                                                                                                                                                </div>\n" +
                "                                                                                                                                                                        </td>\n" +
                "                                                                                                                                                                </tr>\n" +
                "                                                                                                                                                        </tbody>\n" +
                "                                                                                                                                                </table>\n" +
                "                                                                                                                                        </td>\n" +
                "                                                                                                                                </tr>\n" +
                "                                                                                                                        </tbody>\n" +
                "                                                                                                                </table>\n" +
                "                                                                                                        </div>\n" +
                "                                                                                                </div>\n" +
                "                                                                                        </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                </table>\n" +
                "                                                        </div>\n" +
                "                                                        <div\n" +
                "                                                                style=\"background:#f4f4f4;background-color:#f4f4f4;margin:0 auto;max-width:600px\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                        cellspacing=\"0\" role=\"presentation\"\n" +
                "                                                                        style=\"background:#f4f4f4;background-color:#f4f4f4;width:100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                        <td\n" +
                "                                                                                                style=\"border:0 solid transparent;direction:ltr;font-size:0;padding:20px 0;padding-bottom:10px;padding-left:30px;padding-right:30px;padding-top:10px;text-align:center\">\n" +
                "                                                                                                <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                                                                                                        style=\"font-size:0;line-height:0;text-align:left;display:inline-block;width:100%;direction:ltr\">\n" +
                "                                                                                                        <div class=\"mj-column-per-47 mj-outlook-group-fix\"\n" +
                "                                                                                                                style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:47%\">\n" +
                "                                                                                                                <table border=\"0\"\n" +
                "                                                                                                                        cellpadding=\"0\"\n" +
                "                                                                                                                        cellspacing=\"0\"\n" +
                "                                                                                                                        role=\"presentation\"\n" +
                "                                                                                                                        width=\"100%\">\n" +
                "                                                                                                                        <tbody>\n" +
                "                                                                                                                                <tr>\n" +
                "                                                                                                                                        <td\n" +
                "                                                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0\">\n" +
                "                                                                                                                                                <table border=\"0\"\n" +
                "                                                                                                                                                        cellpadding=\"0\"\n" +
                "                                                                                                                                                        cellspacing=\"0\"\n" +
                "                                                                                                                                                        role=\"presentation\"\n" +
                "                                                                                                                                                        width=\"100%\">\n" +
                "                                                                                                                                                        <tbody>\n" +
                "                                                                                                                                                                <tr>\n" +
                "                                                                                                                                                                        <td align=\"left\"\n" +
                "                                                                                                                                                                                style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                                                                <div\n" +
                "                                                                                                                                                                                        style=\"font-family:Helvetica;font-size:16px;font-weight:400;letter-spacing:0;line-height:1.5;text-align:left;color:#444\">\n" +
                "                                                                                                                                                                                        <p><span style=\"font-size:20px\"><strong>Total</strong></span>\n" +
                "                                                                                                                                                                                        </p>\n" +
                "                                                                                                                                                                                </div>\n" +
                "                                                                                                                                                                        </td>\n" +
                "                                                                                                                                                                </tr>\n" +
                "                                                                                                                                                        </tbody>\n" +
                "                                                                                                                                                </table>\n" +
                "                                                                                                                                        </td>\n" +
                "                                                                                                                                </tr>\n" +
                "                                                                                                                        </tbody>\n" +
                "                                                                                                                </table>\n" +
                "                                                                                                        </div>\n" +
                "                                                                                                        <div class=\"mj-column-per-53 mj-outlook-group-fix\"\n" +
                "                                                                                                                style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:53%\">\n" +
                "                                                                                                                <table border=\"0\"\n" +
                "                                                                                                                        cellpadding=\"0\"\n" +
                "                                                                                                                        cellspacing=\"0\"\n" +
                "                                                                                                                        role=\"presentation\"\n" +
                "                                                                                                                        width=\"100%\">\n" +
                "                                                                                                                        <tbody>\n" +
                "                                                                                                                                <tr>\n" +
                "                                                                                                                                        <td\n" +
                "                                                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0\">\n" +
                "                                                                                                                                                <table border=\"0\"\n" +
                "                                                                                                                                                        cellpadding=\"0\"\n" +
                "                                                                                                                                                        cellspacing=\"0\"\n" +
                "                                                                                                                                                        role=\"presentation\"\n" +
                "                                                                                                                                                        width=\"100%\">\n" +
                "                                                                                                                                                        <tbody>\n" +
                "                                                                                                                                                                <tr>\n" +
                "                                                                                                                                                                        <td align=\"left\"\n" +
                "                                                                                                                                                                                style=\"font-size:0;padding:10px 25px;padding-top:0;padding-right:0;padding-bottom:0;padding-left:0;word-break:break-word\">\n" +
                "                                                                                                                                                                                <div\n" +
                "                                                                                                                                                                                        style=\"font-family:Helvetica;font-size:16px;font-weight:400;letter-spacing:0;line-height:1.5;text-align:left;color:#444\">\n" +
                "                                                                                                                                                                                        <p><span style=\"color:#10b981;font-size:20px\"><strong>"+amount+"</strong></span></p>\n" +
                "                                                                                                                                                                                </div>\n" +
                "                                                                                                                                                                        </td>\n" +
                "                                                                                                                                                                </tr>\n" +
                "                                                                                                                                                        </tbody>\n" +
                "                                                                                                                                                </table>\n" +
                "                                                                                                                                        </td>\n" +
                "                                                                                                                                </tr>\n" +
                "                                                                                                                        </tbody>\n" +
                "                                                                                                                </table>\n" +
                "                                                                                                        </div>\n" +
                "                                                                                                </div>\n" +
                "                                                                                        </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                </table>\n" +
                "                                                        </div>\n" +
                "                                                        <div\n" +
                "                                                                style=\"background:0 0;background-color:transparent;margin:0 auto;max-width:600px\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "                                                                        cellspacing=\"0\" role=\"presentation\"\n" +
                "                                                                        style=\"background:0 0;background-color:transparent;width:100%\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                        <td\n" +
                "                                                                                                style=\"border:0 solid transparent;direction:ltr;font-size:0;padding:20px 0;padding-bottom:0;padding-left:20px;padding-right:20px;padding-top:0;text-align:center\">\n" +
                "                                                                                                <div class=\"mj-column-per-100 mj-outlook-group-fix\"\n" +
                "                                                                                                        style=\"font-size:0;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%\">\n" +
                "                                                                                                        <table border=\"0\"\n" +
                "                                                                                                                cellpadding=\"0\"\n" +
                "                                                                                                                cellspacing=\"0\"\n" +
                "                                                                                                                role=\"presentation\"\n" +
                "                                                                                                                style=\"background-color:transparent;border:0 solid transparent;vertical-align:top\"\n" +
                "                                                                                                                width=\"100%\">\n" +
                "                                                                                                                <tbody>\n" +
                "                                                                                                                        <tr>\n" +
                "                                                                                                                                <td\n" +
                "                                                                                                                                        style=\"font-size:0;padding:10px 25px;padding-top:20px;padding-right:10px;padding-bottom:20px;padding-left:10px;word-break:break-word\">\n" +
                "                                                                                                                                        <p\n" +
                "                                                                                                                                                style=\"border-top:solid 1px #ebebeb;font-size:1px;margin:0 auto;width:100%\">\n" +
                "                                                                                                                                        </p>\n" +
                "                                                                                                                                </td>\n" +
                "                                                                                                                        </tr>\n" +
                "                                                                                                                </tbody>\n" +
                "                                                                                                        </table>\n" +
                "                                                                                                </div>\n" +
                "                                                                                        </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                </table>\n" +
                "                                                        </div>\n" +
                "                                                </td>\n" +
                "                                        </tr>\n" +
                "                                </tbody>\n" +
                "                        </table>\n" +
                "                </div>\n" +
                "        </div><amp-img alt=\"signature\" height=\"1\"\n" +
                "                src=\"https://ci3.googleusercontent.com/proxy/r3QJmt9l61DMdOsoUCRjNFq-fPvivSyDJi4iDXAvHpL3e8FOMQidwMPN98BSfKIIapeB9Ira5bMV5qV1WlEy4GcfzuVSDRwjFzsd3AFKE2yl8SqBTFPzSBEabplra6sHp9WSIOQfo8wQeEVIWS9xZ7Y3e8OCO12mqQ3u=s0-d-e1-ft#https://t.mmtrkr.com/opens/amp/169a8b26-041d-4f57-bacf-90a31a5d0d76/3d055c98-9ff3-41d0-a2d6-b00d127c524f\"\n" +
                "                width=\"1\"\n" +
                "                class=\"i-amphtml-element i-amphtml-layout-fixed i-amphtml-layout-size-defined i-amphtml-built i-amphtml-layout\"\n" +
                "                i-amphtml-layout=\"fixed\" style=\"width: 1px; height: 1px;\"><img decoding=\"async\" alt=\"signature\"\n" +
                "                        src=\"https://ci3.googleusercontent.com/proxy/r3QJmt9l61DMdOsoUCRjNFq-fPvivSyDJi4iDXAvHpL3e8FOMQidwMPN98BSfKIIapeB9Ira5bMV5qV1WlEy4GcfzuVSDRwjFzsd3AFKE2yl8SqBTFPzSBEabplra6sHp9WSIOQfo8wQeEVIWS9xZ7Y3e8OCO12mqQ3u=s0-d-e1-ft#https://t.mmtrkr.com/opens/amp/169a8b26-041d-4f57-bacf-90a31a5d0d76/3d055c98-9ff3-41d0-a2d6-b00d127c524f\"\n" +
                "                        class=\"i-amphtml-fill-content i-amphtml-replaced-content\"></amp-img>\n" +
                "        <div\n" +
                "                style=\"color:transparent;visibility:hidden;opacity:0;font-size:0px;border:0;max-height:1px;width:1px;margin:0px;padding:0px;border-width:0px;line-height:0px\">\n" +
                "                <amp-img height=\"1\"\n" +
                "                        src=\"https://ci3.googleusercontent.com/proxy/gEDu0oEkO6OP68BJsy6dtPiGlgBnSKSAJAcSCOJx_ng-odMw9db6OEUHXB7uAnvW_nv7fl26RzLb1_jhEKOc_xJDmH_AEkNLAPzn9gPYKS3qD0TbLTGASujkb0WBH7hsCV2JdN2meI5UevZMN3dtQ-Rpk_5jAISCZ-wq7rreTvTC2A6o2T3H-yFHM9N8h2Vb4kGg66Fj1zD3tJ6IvA=s0-d-e1-ft#https://sp-t1.mmtrkr.com/q/gzMMk92LpwMV3BKvL7O7-w~~/AASgtwA~/RgRnJeWNPlcDc3BjQgplQY1gQ2Xg5PWbUhduZ29idWl0dWFuYW5oQGdtYWlsLmNvbSJYBAAAAnY~\"\n" +
                "                        width=\"1\"\n" +
                "                        class=\"i-amphtml-element i-amphtml-layout-fixed i-amphtml-layout-size-defined i-amphtml-built i-amphtml-layout\"\n" +
                "                        i-amphtml-layout=\"fixed\" style=\"width: 1px; height: 1px;\"><img decoding=\"async\"\n" +
                "                                src=\"https://ci3.googleusercontent.com/proxy/gEDu0oEkO6OP68BJsy6dtPiGlgBnSKSAJAcSCOJx_ng-odMw9db6OEUHXB7uAnvW_nv7fl26RzLb1_jhEKOc_xJDmH_AEkNLAPzn9gPYKS3qD0TbLTGASujkb0WBH7hsCV2JdN2meI5UevZMN3dtQ-Rpk_5jAISCZ-wq7rreTvTC2A6o2T3H-yFHM9N8h2Vb4kGg66Fj1zD3tJ6IvA=s0-d-e1-ft#https://sp-t1.mmtrkr.com/q/gzMMk92LpwMV3BKvL7O7-w~~/AASgtwA~/RgRnJeWNPlcDc3BjQgplQY1gQ2Xg5PWbUhduZ29idWl0dWFuYW5oQGdtYWlsLmNvbSJYBAAAAnY~\"\n" +
                "                                class=\"i-amphtml-fill-content i-amphtml-replaced-content\"></amp-img>\n" +
                "        </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

//    public static String TemplateInvoice() throws IOException {
//        String temp = new String(Files.readAllBytes(Paths.get("D:\\intelliJ\\ArticleManagement\\src\\main\\java\\com\\example\\demo\\file\\TemplateInvoice.txt")));
//        return temp;
//    }
}
