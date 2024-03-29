// Generated by ProtoGen, Version=2.4.1.555, Culture=neutral, PublicKeyToken=17b3b1f090c3ea48.  DO NOT EDIT!
#pragma warning disable 1591, 0612, 3021
#region Designer generated code

using pb = global::Google.ProtocolBuffers;
using pbc = global::Google.ProtocolBuffers.Collections;
using pbd = global::Google.ProtocolBuffers.Descriptors;
using scg = global::System.Collections.Generic;
[global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
public static partial class CreqDTODef {

  #region Extension registration
  public static void RegisterAllExtensions(pb::ExtensionRegistry registry) {
  }
  #endregion
  #region Static variables
  internal static pbd::MessageDescriptor internal__static_CreqDTOPB__Descriptor;
  internal static pb::FieldAccess.FieldAccessorTable<global::CreqDTOPB, global::CreqDTOPB.Builder> internal__static_CreqDTOPB__FieldAccessorTable;
  #endregion
  #region Descriptor
  public static pbd::FileDescriptor Descriptor {
    get { return descriptor; }
  }
  private static pbd::FileDescriptor descriptor;
  
  static CreqDTODef() {
    byte[] descriptorData = global::System.Convert.FromBase64String(
        string.Concat(
          "ChBDcmVxRFRPRGVmLnByb3RvIgsKCUNyZXFEVE9QQkIdChtvcmcuZ2FtZXBy", 
        "b3RvdHlwZS5wcm90by5zcmM="));
    pbd::FileDescriptor.InternalDescriptorAssigner assigner = delegate(pbd::FileDescriptor root) {
      descriptor = root;
      internal__static_CreqDTOPB__Descriptor = Descriptor.MessageTypes[0];
      internal__static_CreqDTOPB__FieldAccessorTable = 
          new pb::FieldAccess.FieldAccessorTable<global::CreqDTOPB, global::CreqDTOPB.Builder>(internal__static_CreqDTOPB__Descriptor,
              new string[] { });
      return null;
    };
    pbd::FileDescriptor.InternalBuildGeneratedFileFrom(descriptorData,
        new pbd::FileDescriptor[] {
        }, assigner);
  }
  #endregion
  
}
#region Messages
[global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
public sealed partial class CreqDTOPB : pb::GeneratedMessage<CreqDTOPB, CreqDTOPB.Builder> {
  private CreqDTOPB() { }
  private static readonly CreqDTOPB defaultInstance = new CreqDTOPB().MakeReadOnly();
  private static readonly string[] _creqDTOPBFieldNames = new string[] {  };
  private static readonly uint[] _creqDTOPBFieldTags = new uint[] {  };
  public static CreqDTOPB DefaultInstance {
    get { return defaultInstance; }
  }
  
  public override CreqDTOPB DefaultInstanceForType {
    get { return DefaultInstance; }
  }
  
  protected override CreqDTOPB ThisMessage {
    get { return this; }
  }
  
  public static pbd::MessageDescriptor Descriptor {
    get { return global::CreqDTODef.internal__static_CreqDTOPB__Descriptor; }
  }
  
  protected override pb::FieldAccess.FieldAccessorTable<CreqDTOPB, CreqDTOPB.Builder> InternalFieldAccessors {
    get { return global::CreqDTODef.internal__static_CreqDTOPB__FieldAccessorTable; }
  }
  
  public override bool IsInitialized {
    get {
      return true;
    }
  }
  
  public override void WriteTo(pb::ICodedOutputStream output) {
    CalcSerializedSize();
    string[] field_names = _creqDTOPBFieldNames;
    UnknownFields.WriteTo(output);
  }
  
  private int memoizedSerializedSize = -1;
  public override int SerializedSize {
    get {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
      return CalcSerializedSize();
    }
  }
  
  private int CalcSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;
    
    size = 0;
    size += UnknownFields.SerializedSize;
    memoizedSerializedSize = size;
    return size;
  }
  public static CreqDTOPB ParseFrom(pb::ByteString data) {
    return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
  }
  public static CreqDTOPB ParseFrom(pb::ByteString data, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
  }
  public static CreqDTOPB ParseFrom(byte[] data) {
    return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
  }
  public static CreqDTOPB ParseFrom(byte[] data, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
  }
  public static CreqDTOPB ParseFrom(global::System.IO.Stream input) {
    return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
  }
  public static CreqDTOPB ParseFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
  }
  public static CreqDTOPB ParseDelimitedFrom(global::System.IO.Stream input) {
    return CreateBuilder().MergeDelimitedFrom(input).BuildParsed();
  }
  public static CreqDTOPB ParseDelimitedFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
    return CreateBuilder().MergeDelimitedFrom(input, extensionRegistry).BuildParsed();
  }
  public static CreqDTOPB ParseFrom(pb::ICodedInputStream input) {
    return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
  }
  public static CreqDTOPB ParseFrom(pb::ICodedInputStream input, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
  }
  private CreqDTOPB MakeReadOnly() {
    return this;
  }
  
  public static Builder CreateBuilder() { return new Builder(); }
  public override Builder ToBuilder() { return CreateBuilder(this); }
  public override Builder CreateBuilderForType() { return new Builder(); }
  public static Builder CreateBuilder(CreqDTOPB prototype) {
    return new Builder(prototype);
  }
  
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
  public sealed partial class Builder : pb::GeneratedBuilder<CreqDTOPB, Builder> {
    protected override Builder ThisBuilder {
      get { return this; }
    }
    public Builder() {
      result = DefaultInstance;
      resultIsReadOnly = true;
    }
    internal Builder(CreqDTOPB cloneFrom) {
      result = cloneFrom;
      resultIsReadOnly = true;
    }
    
    private bool resultIsReadOnly;
    private CreqDTOPB result;
    
    private CreqDTOPB PrepareBuilder() {
      if (resultIsReadOnly) {
        CreqDTOPB original = result;
        result = new CreqDTOPB();
        resultIsReadOnly = false;
        MergeFrom(original);
      }
      return result;
    }
    
    public override bool IsInitialized {
      get { return result.IsInitialized; }
    }
    
    protected override CreqDTOPB MessageBeingBuilt {
      get { return PrepareBuilder(); }
    }
    
    public override Builder Clear() {
      result = DefaultInstance;
      resultIsReadOnly = true;
      return this;
    }
    
    public override Builder Clone() {
      if (resultIsReadOnly) {
        return new Builder(result);
      } else {
        return new Builder().MergeFrom(result);
      }
    }
    
    public override pbd::MessageDescriptor DescriptorForType {
      get { return global::CreqDTOPB.Descriptor; }
    }
    
    public override CreqDTOPB DefaultInstanceForType {
      get { return global::CreqDTOPB.DefaultInstance; }
    }
    
    public override CreqDTOPB BuildPartial() {
      if (resultIsReadOnly) {
        return result;
      }
      resultIsReadOnly = true;
      return result.MakeReadOnly();
    }
    
    public override Builder MergeFrom(pb::IMessage other) {
      if (other is CreqDTOPB) {
        return MergeFrom((CreqDTOPB) other);
      } else {
        base.MergeFrom(other);
        return this;
      }
    }
    
    public override Builder MergeFrom(CreqDTOPB other) {
      if (other == global::CreqDTOPB.DefaultInstance) return this;
      PrepareBuilder();
      this.MergeUnknownFields(other.UnknownFields);
      return this;
    }
    
    public override Builder MergeFrom(pb::ICodedInputStream input) {
      return MergeFrom(input, pb::ExtensionRegistry.Empty);
    }
    
    public override Builder MergeFrom(pb::ICodedInputStream input, pb::ExtensionRegistry extensionRegistry) {
      PrepareBuilder();
      pb::UnknownFieldSet.Builder unknownFields = null;
      uint tag;
      string field_name;
      while (input.ReadTag(out tag, out field_name)) {
        if(tag == 0 && field_name != null) {
          int field_ordinal = global::System.Array.BinarySearch(_creqDTOPBFieldNames, field_name, global::System.StringComparer.Ordinal);
          if(field_ordinal >= 0)
            tag = _creqDTOPBFieldTags[field_ordinal];
          else {
            if (unknownFields == null) {
              unknownFields = pb::UnknownFieldSet.CreateBuilder(this.UnknownFields);
            }
            ParseUnknownField(input, unknownFields, extensionRegistry, tag, field_name);
            continue;
          }
        }
        switch (tag) {
          case 0: {
            throw pb::InvalidProtocolBufferException.InvalidTag();
          }
          default: {
            if (pb::WireFormat.IsEndGroupTag(tag)) {
              if (unknownFields != null) {
                this.UnknownFields = unknownFields.Build();
              }
              return this;
            }
            if (unknownFields == null) {
              unknownFields = pb::UnknownFieldSet.CreateBuilder(this.UnknownFields);
            }
            ParseUnknownField(input, unknownFields, extensionRegistry, tag, field_name);
            break;
          }
        }
      }
      
      if (unknownFields != null) {
        this.UnknownFields = unknownFields.Build();
      }
      return this;
    }
    
  }
  static CreqDTOPB() {
    object.ReferenceEquals(global::CreqDTODef.Descriptor, null);
  }
}

#endregion


#endregion Designer generated code
