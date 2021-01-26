package com.jk.jkproject.ui.msg;

import com.jk.jkproject.net.im.info.MessageEntity;

public interface IUploadProgress {
  void showProgress(int paramInt);
  
  void showStatus(MessageEntity paramMessageEntity);
}
