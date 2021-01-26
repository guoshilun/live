package com.jk.jkproject.net.okhttp.progress.update;

import com.jk.jkproject.net.okhttp.progress.listener.ProgressListener;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class ProgressRequestBody extends RequestBody {
  private BufferedSink bufferedSink;
  
  private final ProgressListener mProgressListener;
  
  private final RequestBody mRequestBody;
  
  public ProgressRequestBody(RequestBody paramRequestBody, ProgressListener paramProgressListener) {
    this.mRequestBody = paramRequestBody;
    this.mProgressListener = paramProgressListener;
  }
  
  private Sink sink(Sink paramSink) {
    return (Sink)new ForwardingSink(paramSink) {
        long bytesWritten = 0L;
        
        long contentLength = 0L;
        
        public void write(Buffer param1Buffer, long param1Long) throws IOException {
          super.write(param1Buffer, param1Long);
          if (this.contentLength == 0L)
            this.contentLength = ProgressRequestBody.this.contentLength(); 
          this.bytesWritten += param1Long;
          if (ProgressRequestBody.this.mProgressListener != null) {
            boolean bool;
            ProgressListener progressListener = ProgressRequestBody.this.mProgressListener;
            param1Long = this.bytesWritten;
            long l = this.contentLength;
            if (param1Long == l) {
              bool = true;
            } else {
              bool = false;
            } 
            progressListener.onProgress(param1Long, l, "", bool);
          } 
        }
      };
  }
  
  public long contentLength() throws IOException {
    return this.mRequestBody.contentLength();
  }
  
  public MediaType contentType() {
    return this.mRequestBody.contentType();
  }
  
  public void writeTo(BufferedSink paramBufferedSink) throws IOException {
    if (this.bufferedSink == null)
      this.bufferedSink = Okio.buffer(sink((Sink)paramBufferedSink)); 
    this.mRequestBody.writeTo(this.bufferedSink);
    this.bufferedSink.flush();
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkproject\net\okhttp\progres\\update\ProgressRequestBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */